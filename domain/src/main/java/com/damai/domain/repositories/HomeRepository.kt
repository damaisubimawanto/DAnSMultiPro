package com.damai.domain.repositories

import com.damai.base.networks.Resource
import com.damai.domain.models.JobPositionListModel
import com.damai.domain.models.JobPositionRequest
import kotlinx.coroutines.flow.Flow

/**
 * Created by damai007 on 18/July/2023
 */
interface HomeRepository {

    @Throws(Exception::class)
    fun getJobPositionList(
        requestModel: JobPositionRequest
    ): Flow<Resource<JobPositionListModel>>
}