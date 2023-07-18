package com.damai.domain.models

import com.damai.base.BaseModel

/**
 * Created by damai007 on 18/July/2023
 */
data class JobDetailModel(
    val jobPositionModel: JobPositionModel
) : BaseModel()
