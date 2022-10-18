package com.ameer.turoevent.businesssearch.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.example.Businesses

@Dao
interface BusinessSearchDao {
    @Query("select * from businesssearchresults order by id")
    fun queryAll(): PagingSource<Int, LocalBusinessSearch>

    /*@Query("select * from businesssearchresults where id like :term || '%' order by value")
    fun searchAll(term: String): PagingSource<Int, LocalWord>*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(businessSearchResponse: Result<ArrayList<Businesses>>)

    @Query("select count(*) from businesssearchresults")
    suspend fun count(): Long
}