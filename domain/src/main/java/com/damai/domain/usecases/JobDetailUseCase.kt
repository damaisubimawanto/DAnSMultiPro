package com.damai.domain.usecases

import com.damai.base.networks.FlowUseCase
import com.damai.base.networks.Resource
import com.damai.domain.models.JobDetailModel
import com.damai.domain.repositories.HomeRepository
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai007 on 18/July/2023
 */
class JobDetailUseCase(
    private val homeRepository: HomeRepository
) : FlowUseCase<String, JobDetailModel>() {

    override suspend fun execute(parameters: String?): Flow<Resource<JobDetailModel>> {
        return homeRepository.getJobDetail(jobId = requireNotNull(parameters))
    }
}