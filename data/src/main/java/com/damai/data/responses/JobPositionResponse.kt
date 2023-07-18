package com.damai.data.responses

import com.damai.base.BaseResponse
import com.google.gson.annotations.SerializedName

/**
 * Created by damai007 on 18/July/2023
 */
class JobPositionResponse : BaseResponse() {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("type")
    var type: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("created_at")
    var createdAt: String? = null

    @SerializedName("company")
    var company: String? = null

    @SerializedName("company_url")
    var companyUrl: String? = null

    @SerializedName("location")
    var location: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("how_to_apply")
    var howToApply: String? = null

    @SerializedName("company_logo")
    var companyLogo: String? = null
}