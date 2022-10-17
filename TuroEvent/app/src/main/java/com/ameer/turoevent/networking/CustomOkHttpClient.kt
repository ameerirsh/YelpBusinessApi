package com.ameer.turoevent.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class CustomOkHttpClient {
    val httpClient : OkHttpClient.Builder = OkHttpClient.Builder()
    val logging = HttpLoggingInterceptor()
    fun getOkHttpClient() : OkHttpClient{
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .header("Authorization", "Bearer ICJIGQZBMC_rOUxTCUDIcGvI2Wt3_m_-U7RTiPg7vLlRBNGuE5AfUxmomx2HQaXtkjm4GptEb9sZnn2a8HfZ50VJMv2GRegSk9sjfHz9PP20lIht6TSAGiMA9BBNY3Yx") // <-- this is the important line
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        httpClient.addNetworkInterceptor(logging)
        return httpClient.build()
    }
}