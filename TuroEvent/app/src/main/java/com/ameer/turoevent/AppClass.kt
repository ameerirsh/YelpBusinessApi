package com.ameer.turoevent

import android.app.Application
import androidx.room.Room
import com.ameer.turoevent.businesssearch.data.AppDatabase
import com.ameer.turoevent.businesssearch.data.BusinessSearchRepository
import com.ameer.turoevent.networking.ApiService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppClass: Application() {
    private val database by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, "database.db").build()
    }

    val businessSearchRepository by lazy { BusinessSearchRepository(database) }
}
