package com.sample.randomquote

import androidx.lifecycle.LiveData
import com.sample.randomquote.database.QuoteDao
import com.sample.randomquote.database.QuoteEntity

class QuoteRepository(
    private val quoteDao: QuoteDao
) {

    fun getQuotes(): LiveData<List<QuoteEntity>> = quoteDao.getAllQuotes()

    suspend fun insertQuote(quoteEntity: QuoteEntity) = quoteDao.insertQuotes(quoteEntity)
}