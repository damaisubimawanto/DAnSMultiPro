package com.damai.dansmultipro.application

import android.app.Application
import com.damai.dansmultipro.modules.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Created by damai007 on 18/July/2023
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(allModules)
        }
    }
}