package com.damai.dansmultipro.ui.jobdetail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.damai.base.BaseViewModel
import com.damai.base.coroutines.DispatcherProvider
import com.damai.base.extensions.asLiveData
import com.damai.base.networks.Resource
import com.damai.base.utils.Event
import com.damai.domain.models.JobPositionModel
import com.damai.domain.usecases.JobDetailUseCase
import kotlinx.coroutines.launch

/**
 * Created by damai007 on 18/July/2023
 */
class JobDetailViewModel(
    app: Application,
    private val jobDetailUseCase: JobDetailUseCase,
    private val dispatcher: DispatcherProvider
) : BaseViewModel(app = app) {

    //region Live Data
    private val _jobDetailLiveData = MutableLiveData<JobPositionModel>()
    val jobDetailLiveData = _jobDetailLiveData.asLiveData()

    private val _jobDetailErrorLiveData = MutableLiveData<Event<String>>()
    val jobDetailErrorLiveData = _jobDetailErrorLiveData.asLiveData()
    //endregion `Live Data`

    //region Variables
    var jobId: String? = null
    //endregion `Variables`

    fun getJobDetail() {
        viewModelScope.launch(dispatcher.io()) {
            jobDetailUseCase(jobId).collect { resource ->
                when (resource) {
                    is Resource.Success -> {
                        resource.model?.jobPositionModel?.let { jobPositionMOdel ->
                            _jobDetailLiveData.postValue(jobPositionMOdel)
                        } ?: run {
                            Event("Empty job detail").let(
                                _jobDetailErrorLiveData::postValue
                            )
                        }
                    }
                    is Resource.Error -> {
                        Event(resource.errorMessage.orEmpty()).let(
                            _jobDetailErrorLiveData::postValue
                        )
                    }
                }
            }
        }
    }
}