package com.damai.domain.usecases

import com.damai.base.networks.FlowUseCase
import com.damai.base.networks.Resource
import com.damai.domain.models.JobPositionListModel
import com.damai.domain.models.JobPositionRequest
import com.damai.domain.repositories.HomeRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai007 on 18/July/2023
 */
class JobPositionListWithFilterUseCase(
    private val homeRepository: HomeRepository
) : FlowUseCase<JobPositionRequest, JobPositionListModel>() {

    override suspend fun execute(parameters: JobPositionRequest?): Flow<Resource<JobPositionListModel>> {
        requireNotNull(parameters).let {
            return homeRepository.getJobPositionList(
                page = it.page,
                description = it.keyword,
                location = it.location,
                isFullTime = it.isFullTime
            )
        }
    }
}