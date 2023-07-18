package com.damai.data.apiservices

import com.damai.data.responses.JobPositionResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by damai007 on 18/July/2023
 */
interface HomeService {

    @GET("/api/recruitment/positions.json")
    suspend fun getJobPositionList(
        @Query("page") page: Int,
        @Query("description") description: String?,
        @Query("location") location: String?,
        @Query("full_time") fullTime: Boolean?
    ): List<JobPositionResponse?>

    @GET("/api/recruitment/positions/{jobId}")
    suspend fun getJobDetail(
        @Path("jobId") jobId: String
    ): JobPositionResponse
}