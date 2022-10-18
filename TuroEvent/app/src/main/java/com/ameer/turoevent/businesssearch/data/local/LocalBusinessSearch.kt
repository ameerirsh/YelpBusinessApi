package com.ameer.turoevent.businesssearch.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "businesssearchresults")
data class LocalBusinessSearch (
        @PrimaryKey val id: String
        )