package com.ameer.turoevent.networking

import com.example.example.BusinessSearchApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    suspend fun getBusiness(@Query("term") term: String, @Query("location") location: String) : Result<BusinessSearchApiResponse>
}