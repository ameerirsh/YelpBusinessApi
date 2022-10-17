package com.ameer.turoevent.networking

import com.example.example.BusinessSearchApiResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun getBusiness(@Query("term") term: String, @Query("location") location: String) : BusinessSearchApiResponse

    companion object {
        var apiService: ApiService? = null
        val okHttpClient = CustomOkHttpClient().getOkHttpClient()

        /**
         *
         */
        fun getInstance() : ApiService {
            if(apiService == null) {
                apiService = retrofitInstance().create(ApiService::class.java)
            }
            return apiService!!
        }

        /**
         *
         */
        fun retrofitInstance() : Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://api.yelp.com/v3/businesses/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}