package com.ameer.turoevent

import com.ameer.turoevent.networking.ApiService
import org.junit.Test
import retrofit2.Retrofit

class RetrofitClientTest {
    @Test
    fun testRetrofitInstance() {
        //Get an instance of Retrofit
        val instance: Retrofit = ApiService.retrofitInstance()
        //Assert that, Retrofit's base url matches to our BASE_URL
        assert(instance.baseUrl().toUrl().toString() == "https://api.yelp.com/v3/businesses/")
    }
}