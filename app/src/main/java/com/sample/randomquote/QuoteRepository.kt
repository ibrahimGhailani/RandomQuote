package com.sample.randomquote

import android.util.Log
import androidx.lifecycle.LiveData
import com.sample.randomquote.database.QuoteDao
import com.sample.randomquote.database.QuoteEntity
import com.sample.randomquote.network.QuoteRemoteSource
import java.lang.Exception
import java.net.UnknownHostException

class QuoteRepository(
    private val quoteDao: QuoteDao,
    private val remoteSource: QuoteRemoteSource
) {

    fun getQuotes(): LiveData<List<QuoteEntity>> = quoteDao.getAllQuotes()

    suspend fun refreshQuotes() {
        try {
            val response = remoteSource.refreshQuotes()

            if (response.isSuccessful) {
                response.body()?.let { quotesDto ->
                    quoteDao.insertQuotes(
                        *quotesDto.map {
                            QuoteEntity(
                                id = it.id,
                                author = it.author,
                                quote = it.quote,
                                rating = it.rating
                            )
                        }.toTypedArray()
                    )
                }
            } else {
                Log.d("QuoteRepo", "Retrofit error")
            }
        } catch (e: UnknownHostException) {
            //There is no internet connection
            e.printStackTrace()
        }
    }

    suspend fun insertQuote(quoteEntity: QuoteEntity) = quoteDao.insertQuotes(quoteEntity)
}