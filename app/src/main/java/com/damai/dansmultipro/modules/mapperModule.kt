package com.damai.dansmultipro.modules

import com.damai.data.mappers.JobPositionResponseToJobPositionModelMapper
import org.koin.dsl.module

/**
 * Created by damai007 on 18/July/2023
 */

val mapperModule = module {
    factory {
        JobPositionResponseToJobPositionModelMapper()
    }
}