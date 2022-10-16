package com.ameer.turoevent.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class CustomOkHttpClient {
    val httpClient : OkHttpClient.Builder = OkHttpClient.Builder()
    val logging = HttpLoggingInterceptor()
    public fun getOkHttpClient() : OkHttpClient{
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Authorization", "Bearer 2ROaa2Rh9qu3WVTCms8FoVE4mSfHQHC7QJua95-kKT-PqzIlLSrs4tmHVdtdFw_66-JNfRiJmbCByHTvFNy5dQq-tpfS4FrPpupIzKlgELR3br-r5trpeFhrCRgwWnYx") // <-- this is the important line
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        httpClient.addNetworkInterceptor(logging)
        return httpClient.build()
    }
}