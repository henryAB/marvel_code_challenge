package com.ealmazan.marvelcodechallenge.framework.rest

import com.ealmazan.marvelcodechallenge.framework.util.HashUtil
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

const val BASE_URL = "https://gateway.marvel.com/"
const val TS_URL_QUERY = "ts"
const val API_KEY_URL_QUERY = "apikey"
const val HASH_URL_QUERY = "hash"
const val HASH_PROTOCOL = "MD5"
const val PUBLIC_KEY = "2583f7b3361dee1749ed592f721fdc63"
const val PRIVATE_KEY = "f7306b67755aa7817d88bd76b123f9605d3c329b"

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
        val timestamp = Date().time.toString()

        builder.addInterceptor { chain ->
            val defaultRequest = chain.request()
            val defaultHttpUrl = defaultRequest.url
            val hash = HashUtil.hash("${timestamp}$PRIVATE_KEY$PUBLIC_KEY", HASH_PROTOCOL)
            val httpUrl = defaultHttpUrl.newBuilder()
                .addQueryParameter(TS_URL_QUERY, timestamp)
                .addQueryParameter(API_KEY_URL_QUERY, PUBLIC_KEY)
                .addQueryParameter(HASH_URL_QUERY, hash)
                .build()
            val requestBuilder = defaultRequest.newBuilder().url(httpUrl)
            chain.proceed(requestBuilder.build())
        }
        return builder.build()
    }

}