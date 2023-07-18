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
class JobPositionListUseCase(
    private val homeRepository: HomeRepository
) : FlowUseCase<Int, JobPositionListModel>() {

    override suspend fun execute(parameters: Int?): Flow<Resource<JobPositionListModel>> {
        return homeRepository.getJobPositionList(page = requireNotNull(parameters))
    }
}