package com.ameer.turoevent.businesssearch.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ameer.turoevent.businesssearch.BusinessListState
import com.ameer.turoevent.businesssearch.MainViewModel
import com.ameer.turoevent.ui.theme.TuroEventTheme
import com.example.example.Businesses
import dagger.hilt.android.AndroidEntryPoint
import com.ameer.turoevent.R
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TuroEventTheme {
                val mainViewModel = hiltViewModel<MainViewModel>()
                val context = LocalContext.current
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    val state by mainViewModel.businessListState.collectAsState()
                    mainViewModel.getPizzaAndBeerList()
                    state.let { state ->
                        when (state) {
                            BusinessListState.START -> {

                            }
                            BusinessListState.LOADING -> {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier.fillMaxSize()
                                ) {
                                    CircularProgressIndicator(color = colorResource(id = R.color.purple_700))
                                }
                            }
                            is BusinessListState.FAILURE -> {
                                val message = state.message
                                LaunchedEffect(key1 = message) {
                                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                                }
                            }

                            BusinessListState.SUCCESS -> {
                                BusinessList(businessesList = mainViewModel.busListResponse.distinct())
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BusinessList(businessesList: List<Businesses>) {
    var selectedIndex by remember { mutableStateOf(-1) }
    LazyColumn {
        itemsIndexed(items = businessesList) { index, item ->
            SearchResultItem(businesses = item, index, selectedIndex) {
                    i -> selectedIndex = i
            }
        }
    }
}
