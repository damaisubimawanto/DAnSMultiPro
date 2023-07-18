package com.damai.dansmultipro.modules

import com.damai.dansmultipro.ui.MainViewModel
import com.damai.dansmultipro.ui.jobdetail.JobDetailViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by damai007 on 18/July/2023
 */

val viewModelModule = module {
    viewModel {
        MainViewModel(
            app = androidApplication(),
            jobPositionListUseCase = get(),
            jobPositionListWithFilterUseCase = get(),
            dispatcher = get()
        )
    }

    viewModel {
        JobDetailViewModel(
            app = androidApplication(),
            jobDetailUseCase = get(),
            dispatcher = get()
        )
    }
}