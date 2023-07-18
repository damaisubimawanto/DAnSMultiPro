package com.damai.dansmultipro.modules

import com.damai.base.coroutines.DispatcherProvider
import com.damai.base.coroutines.DispatcherProviderImpl
import com.damai.base.utils.Constants.BASE_URL
import com.damai.base.utils.Constants.TIMEOUT
import com.damai.data.apiservices.HomeService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by damai007 on 18/July/2023
 */

val networkModule = module {
    single { GsonBuilder().create() }

    single {
        val logging = HttpLoggingInterceptor()
        logging.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }

        OkHttpClient.Builder().apply{
            connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            readTimeout(TIMEOUT, TimeUnit.SECONDS)
            cache(null)
            addInterceptor(logging)
        }.build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
    }

    factory {
        get<Retrofit>().create(HomeService::class.java)
    }

    factory<DispatcherProvider> {
        DispatcherProviderImpl()
    }
}