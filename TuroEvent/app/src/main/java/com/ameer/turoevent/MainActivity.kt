package com.ameer.turoevent

import BusinessItem
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import com.ameer.turoevent.ui.theme.TuroEventTheme
import com.example.example.Businesses

class MainActivity : ComponentActivity() {
    val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TuroEventTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    BusinessList(businessesList = mainViewModel.businessListResponse)
                    mainViewModel.getPizzaAndBeerList()
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
            BusinessItem(businesses = item, index, selectedIndex) {
                    i -> selectedIndex = i
            }
        }
    }
}
