package com.ameer.turoevent.businesssearch.data.local

import androidx.paging.*
import com.ameer.turoevent.businesssearch.data.AppDatabase
import com.example.example.BusinessSearchApiResponse
import com.example.example.Businesses
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BusinessSearchStore(database: AppDatabase) {
    private val businessSearch = database.businessSearch

    fun all(): Flow<PagingData<Businesses>> = pagingWord { businessSearch.queryAll() }

    //fun all(term: String): Flow<PagingData<Word>> = pagingWord { businessSearch.searchAll(term) }

    suspend fun save(businessSearchResults: Result<BusinessSearchApiResponse>) {
        this.businessSearch.insert(businessSearchResults.map { it.businesses })
    }

    suspend fun isEmpty(): Boolean = businessSearch.count() == 0L
}

private fun pagingWord(
    block: () -> PagingSource<Int, LocalBusinessSearch>,
): Flow<PagingData<Businesses>> =
    Pager(PagingConfig(pageSize = 20)) { block() }.flow
        .map { page -> page.map { localBusiness -> localBusiness.fromLocal() } }

private fun Businesses.toLocal() = LocalBusinessSearch(
    id = id!!,
)

private fun LocalBusinessSearch.fromLocal() = Businesses(
    id = id,
)