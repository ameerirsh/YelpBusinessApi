package com.ameer.turoevent.businesssearch.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ameer.turoevent.businesssearch.data.local.BusinessSearchDao
import com.ameer.turoevent.businesssearch.data.local.LocalBusinessSearch

@Database(entities = [LocalBusinessSearch::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val businessSearch: BusinessSearchDao
}