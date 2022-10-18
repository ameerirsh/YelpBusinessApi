package com.ameer.turoevent.businesssearch

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.ameer.turoevent.AppClass
import com.ameer.turoevent.analytics.AnalyticsTracker
import com.ameer.turoevent.analytics.EventConstants.Companion.EVENT_API_SEARCH
import com.ameer.turoevent.analytics.EventConstants.Companion.EVENT_NETWORK_ERROR
import com.ameer.turoevent.networking.ApiService
import com.example.example.Businesses
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val CURRENT_ADDRESS = "111 Sutter St, San Francisco,CA"

private const val SEARCH_KEY_PIZZA = "pizza"

private const val SEARCH_KEY_BEER = "beer"

@HiltViewModel
class MainViewModel @Inject constructor(var apiService: ApiService) : ViewModel() {
    //private val businessSearchRepository = getApplication<AppClass>().businessSearchRepository
    var analyticsTracker: AnalyticsTracker = AnalyticsTracker()
    var errorMessage: String by mutableStateOf("")
    var busListResponse: List<Businesses> by mutableStateOf(listOf())
    var pizzaList: List<Businesses> by mutableStateOf(listOf())
    var beerList: List<Businesses> by mutableStateOf(listOf())
    val businessListState = MutableStateFlow<BusinessListState>(BusinessListState.START)

    private val allBusiness = MutableStateFlow(emptyFlow<PagingData<Businesses>>())

    /*fun getPizzaAndBeerListNew() {
        viewModelScope.launch {
            try {
                allBusiness.value = businessSearchRepository.searchResults()
            } catch (e: Exception) {

            }
        }
    }*/

    fun getPizzaAndBeerList() {
            viewModelScope.launch {
            try {
                var pizzaCr = async { apiService.getBusiness(SEARCH_KEY_PIZZA, CURRENT_ADDRESS) }
                pizzaCr.await().onSuccess {
                    pizzaList = it.businesses
                    analyticsTracker.addEvent(EVENT_API_SEARCH, "API Search Success $SEARCH_KEY_PIZZA")
                }.onFailure {
                    businessListState.emit(BusinessListState.FAILURE(it.localizedMessage))
                }

                var beerCr = async { apiService.getBusiness(SEARCH_KEY_BEER, CURRENT_ADDRESS) }
                beerCr.await().onSuccess {
                    beerList = it.businesses
                    analyticsTracker.addEvent(EVENT_API_SEARCH, "API Search Success $SEARCH_KEY_BEER")
                }.onFailure {
                    businessListState.emit(BusinessListState.FAILURE(it.localizedMessage))
                    analyticsTracker.addEvent(EVENT_API_SEARCH, "API Search Success beer")
                }
                busListResponse = pizzaList + beerList
                businessListState.emit(BusinessListState.SUCCESS)
            } catch (exception: java.lang.Exception) {
                errorMessage = exception.message.toString()
                analyticsTracker.addEvent(EVENT_NETWORK_ERROR, "$errorMessage")
            }
        }
    }
}

sealed class BusinessListState {
    object START : BusinessListState()
    object LOADING : BusinessListState()
    object SUCCESS : BusinessListState()
    data class FAILURE(val message: String) : BusinessListState()
}



