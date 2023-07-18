package com.damai.dansmultipro.modules

import com.damai.domain.usecases.JobPositionListUseCase
import org.koin.dsl.module

/**
 * Created by damai007 on 18/July/2023
 */

val useCaseModule = module {
    single {
        JobPositionListUseCase(
            homeRepository = get()
        )
    }
}