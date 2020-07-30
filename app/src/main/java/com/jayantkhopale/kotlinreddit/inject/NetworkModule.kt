package com.jayantkhopale.kotlinreddit.inject

import com.jayantkhopale.kotlinreddit.BuildConfig
import com.jayantkhopale.kotlinreddit.api.NewsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

@Module
@InstallIn(ActivityComponent::class)
object NetworkModule {

    @Provides
    fun provideNewsService(okHttpClient: OkHttpClient): NewsService {
        return Retrofit.Builder()
            .baseUrl("https://www.reddit.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.callTimeout(4000, TimeUnit.MILLISECONDS)
        if (BuildConfig.DEBUG) {
            okHttpBuilder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        return okHttpBuilder.build()
    }
}