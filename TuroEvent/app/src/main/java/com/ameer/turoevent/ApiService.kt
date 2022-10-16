package com.ameer.turoevent

import com.example.example.Businesses
import com.example.example.ExampleJson2KtKotlin
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun getBusiness(@Query("term") term: String, @Query("location") location: String) : ExampleJson2KtKotlin

    companion object {
        var apiService: ApiService? = null
        val httpClient : OkHttpClient.Builder = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        fun getInstance() : ApiService {
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Authorization", "Bearer 2ROaa2Rh9qu3WVTCms8FoVE4mSfHQHC7QJua95-kKT-PqzIlLSrs4tmHVdtdFw_66-JNfRiJmbCByHTvFNy5dQq-tpfS4FrPpupIzKlgELR3br-r5trpeFhrCRgwWnYx") // <-- this is the important line
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            httpClient.addNetworkInterceptor(logging)
            val okHttpClient = httpClient.build()
            if(apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://api.yelp.com/v3/businesses/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}