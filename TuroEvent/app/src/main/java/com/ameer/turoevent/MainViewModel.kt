package com.ameer.turoevent

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.example.Businesses
import com.example.example.ExampleJson2KtKotlin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    lateinit var businessResponse: ExampleJson2KtKotlin
    var businessListResponse: List<Businesses> by mutableStateOf(listOf())
    var errorMessage: String by mutableStateOf("")
    fun getPizzaAndBeerList() {
        val apiService = ApiService.getInstance()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val pizzaList = async { apiService.getBusiness("pizza","111 Sutter St, San Francisco,CA") }
                val beerList = async { apiService.getBusiness("beer","111 Sutter St, San Francisco,CA") }
                businessListResponse = pizzaList.await().businesses + beerList.await().businesses
            } catch (exception: java.lang.Exception) {
                errorMessage = exception.message.toString()
            }
        }
    }

    fun getBusinessList() {
        viewModelScope.launch {
            val apiService = ApiService.getInstance()
            try {
                val response = apiService.getBusiness("pizza","111 Sutter St, San Francisco,CA")
                businessListResponse = response.businesses
                businessResponse = response
            }
            catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

}


