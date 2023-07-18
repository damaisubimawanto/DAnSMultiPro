package com.damai.data.apiservices

import com.damai.data.responses.JobPositionResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by damai007 on 18/July/2023
 */
interface HomeService {

    @GET("/api/recruitment/positions.json")
    suspend fun getJobPositionList(
        @Query("page") page: Int
    ): List<JobPositionResponse>
}