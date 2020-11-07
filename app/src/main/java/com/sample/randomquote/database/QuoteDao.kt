package com.sample.randomquote.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotes(vararg quotes: QuoteEntity)

    @Query("select * from ${QuoteEntity.TABLE_NAME}")
    fun getAllQuotes(): LiveData<List<QuoteEntity>>
}