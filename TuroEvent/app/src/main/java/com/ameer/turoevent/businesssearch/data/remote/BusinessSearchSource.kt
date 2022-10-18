package com.ameer.turoevent.businesssearch.data.remote

import com.ameer.turoevent.networking.ApiService
import com.example.example.BusinessSearchApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

const val CURRENT_ADDRESS = "111 Sutter St, San Francisco,CA"

private const val SEARCH_KEY_PIZZA = "pizza"

private const val SEARCH_KEY_BEER = "beer"

class BusinessSearchSource @Inject constructor(var apiService: ApiService?) {
    suspend fun load(): Result<BusinessSearchApiResponse> = withContext(Dispatchers.IO) {
        apiService?.getBusiness(SEARCH_KEY_PIZZA, CURRENT_ADDRESS)!!.onSuccess {
            it.businesses
        }
    }
}