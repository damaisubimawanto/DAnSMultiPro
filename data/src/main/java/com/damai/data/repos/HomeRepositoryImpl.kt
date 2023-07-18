package com.damai.data.repos

import com.damai.base.coroutines.DispatcherProvider
import com.damai.base.networks.NetworkResource
import com.damai.base.networks.Resource
import com.damai.base.utils.Constants.SUCCESS_CODE
import com.damai.base.utils.Constants.SUCCESS_MESSAGE
import com.damai.data.apiservices.HomeService
import com.damai.data.mappers.JobPositionResponseToJobPositionModelMapper
import com.damai.domain.models.JobPositionListModel
import com.damai.domain.models.JobPositionRequest
import com.damai.domain.repositories.HomeRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai007 on 18/July/2023
 */
class HomeRepositoryImpl(
    private val homeService: HomeService,
    private val dispatcher: DispatcherProvider,
    private val jobPositionResponseMapper: JobPositionResponseToJobPositionModelMapper
) : HomeRepository {

    override fun getJobPositionList(
        requestModel: JobPositionRequest
    ): Flow<Resource<JobPositionListModel>> {
        return object : NetworkResource<JobPositionListModel>(
            dispatcherProvider = dispatcher
        ) {
            override suspend fun remoteFetch(): JobPositionListModel {
                val response = homeService.getJobPositionList(requestModel.page)
                return JobPositionListModel(
                    list = response.filterNotNull().map {
                        jobPositionResponseMapper.map(it)
                    }
                ).also {
                    it.status = SUCCESS_CODE
                    it.message = SUCCESS_MESSAGE
                }
            }
        }.asFlow()
    }
}