package com.ameer.turoevent.businesssearch.data

import androidx.paging.PagingData
import com.ameer.turoevent.businesssearch.data.local.BusinessSearchStore
import com.ameer.turoevent.businesssearch.data.remote.BusinessSearchSource
import com.example.example.Businesses
import kotlinx.coroutines.flow.Flow

class BusinessSearchRepository (
    private val businessSearchSource: BusinessSearchSource,
    private val businessSearchStore: BusinessSearchStore,
) {

    constructor(database: AppDatabase) : this(
        businessSearchSource = BusinessSearchSource(null),
        businessSearchStore = BusinessSearchStore(database),
    )

    suspend fun searchResults(): Flow<PagingData<Businesses>> = businessSearchStore.ensureIsNotEmpty().all()

    /*suspend fun allWords(term: String): Flow<PagingData<Businesses>> =
        businessSearchStore.ensureIsNotEmpty().all(term)*/

    private suspend fun BusinessSearchStore.ensureIsNotEmpty() = apply {
        if (isEmpty()) {
            val businessResults = businessSearchSource.load()
            save(businessResults)
        }
    }

}