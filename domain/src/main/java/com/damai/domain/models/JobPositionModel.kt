package com.damai.domain.models

/**
 * Created by damai007 on 18/July/2023
 */
data class JobPositionModel(
    val id: String,
    val title: String?,
    val company: String?,
    val companyUrl: String?,
    val companyLogo: String?,
    val location: String?,
    val description: String?,
    val url: String?,
    val howToApply: String?,
    val type: String?
)
