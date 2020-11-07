package com.sample.randomquote.createquote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.randomquote.ServiceLocator
import com.sample.randomquote.database.QuoteEntity
import kotlinx.coroutines.launch

class CreateQuoteViewModel : ViewModel() {
    private val quoteRepository = ServiceLocator.quoteRepository


    fun createQuote(author: String, quote: String) {
        viewModelScope.launch {
            quoteRepository.insertQuote(
                QuoteEntity(
                    author = author,
                    quote = quote
                )
            )
        }
    }
}