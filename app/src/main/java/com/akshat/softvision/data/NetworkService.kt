package com.akshat.softvision.data

import com.akshat.softvision.BuildConfig
import com.akshat.softvision.model.Response
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Akshat on 22/06/20.
 */
interface NetworkService {

    @GET("s/2iodh4vg0eortkl/facts.json")
    suspend fun getData(): retrofit2.Response<Response>


    companion object {

        operator fun invoke(): NetworkService {

            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                    else HttpLoggingInterceptor.Level.NONE
            }

            val clientBuilder = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://dl.dropboxusercontent.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(clientBuilder)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(NetworkService::class.java)
        }


    }


}