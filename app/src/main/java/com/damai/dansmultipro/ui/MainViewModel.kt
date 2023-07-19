package com.damai.dansmultipro.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.damai.base.BaseViewModel
import com.damai.base.coroutines.DispatcherProvider
import com.damai.base.extensions.asLiveData
import com.damai.base.extensions.orFalse
import com.damai.base.networks.Resource
import com.damai.domain.models.AccountModel
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

    private val _isFilterBoxShown = MutableLiveData(false)
    val isFilterBoxShown = _isFilterBoxShown.asLiveData()

    private val _accountModelLiveData = MutableLiveData<AccountModel>()
    val accountModelLiveData = _accountModelLiveData.asLiveData()
    //endregion `Live Data`

    //region Variables
    var pagination = 1
    var isResetList = false
    var isPaginationContinue = true
    var filterLocation: String? = null
    var filterFullTime: Boolean? = null
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
        keyword: String?
    ) {
        viewModelScope.launch(dispatcher.io()) {
            val request = JobPositionRequest(
                keyword = keyword,
                location = filterLocation,
                isFullTime = filterFullTime,
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
                        if (_jobPositionListLiveData.value.isNullOrEmpty()) {
                            isResetList = true
                            _jobPositionListLiveData.postValue(listOf())
                        }
                    }
                }
            }
        }
    }

    fun showHideTheFilterBox() {
        (_isFilterBoxShown.value.orFalse().not()).let(
            _isFilterBoxShown::setValue
        )
    }

    fun resetList() {
        _jobPositionListLiveData.postValue(listOf())
    }

    fun dummyAccount() {
        AccountModel(
            name = "Damai Subimawanto",
            photoProfile = "https://pbs.twimg.com/profile_images/1532963504721866753/0Cwg3Enx_400x400.jpg"
        ).let(_accountModelLiveData::setValue)
    }
}