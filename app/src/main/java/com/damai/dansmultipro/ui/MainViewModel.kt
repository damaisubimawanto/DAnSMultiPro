package com.damai.dansmultipro.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.damai.base.BaseViewModel
import com.damai.base.coroutines.DispatcherProvider
import com.damai.base.extensions.asLiveData
import com.damai.base.networks.Resource
import com.damai.domain.models.JobPositionModel
import com.damai.domain.models.JobPositionRequest
import com.damai.domain.usecases.JobPositionListUseCase
import com.damai.domain.usecases.JobPositionListWithFilterUseCase
import kotlinx.coroutines.launch

/**
 * Created by damai007 on 18/July/2023
 */
class MainViewModel(
    app: Application,
    private val jobPositionListUseCase: JobPositionListUseCase,
    private val jobPositionListWithFilterUseCase: JobPositionListWithFilterUseCase,
    private val dispatcher: DispatcherProvider
) : BaseViewModel(app = app) {

    //region Live Data
    private val _jobPositionListLiveData = MutableLiveData<List<JobPositionModel>>()
    val jobPositionListLiveData = _jobPositionListLiveData.asLiveData()
    //endregion `Live Data`

    //region Variables
    var pagination = 1
    var isPaginationContinue = true
    //endregion `Variables`

    fun getJobPositionList() {
        viewModelScope.launch(dispatcher.io()) {
            jobPositionListUseCase(pagination).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        resource.model?.list?.let { response ->
                            var tempData = _jobPositionListLiveData.value?.toMutableList()
                            if (tempData == null) {
                                tempData = mutableListOf()
                            }
                            tempData.addAll(response)
                            _jobPositionListLiveData.postValue(tempData.toList())
                        }
                    }
                    is Resource.Error -> {
                        isPaginationContinue = false
                    }
                }
            }
        }
    }

    fun getJobPositionListByFilter(
        keyword: String?,
        location: String?,
        isFullTime: Boolean?
    ) {
        viewModelScope.launch(dispatcher.io()) {
            val request = JobPositionRequest(
                keyword = keyword,
                location = location,
                isFullTime = isFullTime,
                page = pagination
            )

            jobPositionListWithFilterUseCase(request).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        resource.model?.list?.let { response ->
                            var tempData = _jobPositionListLiveData.value?.toMutableList()
                            if (tempData == null) {
                                tempData = mutableListOf()
                            }
                            tempData.addAll(response)
                            _jobPositionListLiveData.postValue(tempData.toList())
                        }
                    }
                    is Resource.Error -> {
                        isPaginationContinue = false
                    }
                }
            }
        }
    }
}