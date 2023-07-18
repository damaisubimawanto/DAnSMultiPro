package com.damai.domain.models

/**
 * Created by damai007 on 18/July/2023
 */
data class JobPositionRequest(
    val keyword: String?,
    val location: String?,
    val isFullTime: Boolean?,
    val page: Int
)
