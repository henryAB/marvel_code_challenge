package com.ealmazan.marvelcodechallenge.framework.rest

import com.ealmazan.marvelcodechallenge.framework.util.HashUtil
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

const val BASE_URL = "https://gateway.marvel.com/"

class MarvelAPI {

    private var retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(buildOkHttp())
            .baseUrl(BASE_URL)
            .build()

    var api: MarvelService = retrofit.create(MarvelService::class.java)

    private fun buildOkHttp(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
        builder.addInterceptor(loggingInterceptor)
        // TODO fix passwords
        val publicKey = "2583f7b3361dee1749ed592f721fdc63"
        val timestamp = Date().time.toString()

        builder.addInterceptor { chain ->
            val defaultRequest = chain.request()
            val defaultHttpUrl = defaultRequest.url
            val hash = HashUtil.hash("${timestamp}f7306b67755aa7817d88bd76b123f9605d3c329b$publicKey", "MD5")
            val httpUrl = defaultHttpUrl.newBuilder()
                .addQueryParameter("ts", timestamp)
                .addQueryParameter("apikey", publicKey)
                .addQueryParameter("hash", hash)
                .build()
            val requestBuilder = defaultRequest.newBuilder().url(httpUrl)
            chain.proceed(requestBuilder.build())
        }
        return builder.build()
    }

}