package com.damai.dansmultipro.modules

import com.damai.data.repos.HomeRepositoryImpl
import com.damai.domain.repositories.HomeRepository
import org.koin.dsl.module

/**
 * Created by damai007 on 18/July/2023
 */

val repositoryModule = module {
    single<HomeRepository> {
        HomeRepositoryImpl(
            homeService = get(),
            dispatcher = get(),
            jobPositionResponseMapper = get()
        )
    }
}