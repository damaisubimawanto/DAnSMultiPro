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
import kotlinx.coroutines.launch

/**
 * Created by damai007 on 18/July/2023
 */
class MainViewModel(
    app: Application,
    private val jobPositionListUseCase: JobPositionListUseCase,
    private val dispatcher: DispatcherProvider
) : BaseViewModel(app = app) {

    //region Live Data
    private val _jobPositionListLiveData = MutableLiveData<List<JobPositionModel>>()
    val jobPositionListLiveData = _jobPositionListLiveData.asLiveData()
    //endregion `Live Data`

    fun getJobPositionList() {
        viewModelScope.launch(dispatcher.io()) {
            val requestBody = JobPositionRequest(
                page = 1
            )
            jobPositionListUseCase(requestBody).collect { resource ->
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

                    }
                }
            }
        }
    }
}