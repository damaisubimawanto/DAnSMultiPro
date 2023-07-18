package com.damai.base

import com.google.gson.annotations.SerializedName

/**
 * Created by damai007 on 18/July/2023
 */
open class BaseResponse {
    @SerializedName("status")
    val status: Int? = null

    @SerializedName("message")
    val message: String? = null
}