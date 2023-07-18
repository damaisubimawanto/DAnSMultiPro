package com.damai.dansmultipro.modules

import com.damai.base.navigations.AppNavigation
import com.damai.base.navigations.AppNavigationImpl
import com.damai.dansmultipro.navigation.PageNavigationApi
import com.damai.dansmultipro.navigation.PageNavigationApiImpl
import org.koin.dsl.module

/**
 * Created by damai007 on 18/July/2023
 */

val pageNavigationModule = module {
    factory<AppNavigation> {
        AppNavigationImpl()
    }

    factory<PageNavigationApi> {
        PageNavigationApiImpl(
            appNavigation = get()
        )
    }
}