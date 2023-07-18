package com.damai.domain.models

import com.damai.base.BaseModel

/**
 * Created by damai007 on 18/July/2023
 */
data class JobPositionListModel(
    val list: List<JobPositionModel>? = null
) : BaseModel()
