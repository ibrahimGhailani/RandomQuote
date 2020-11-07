package com.sample.randomquote.quotelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.randomquote.Quote
import com.sample.randomquote.QuoteRepository
import com.sample.randomquote.ServiceLocator
import kotlinx.coroutines.launch

class QuoteListViewModel : ViewModel() {

    private val quoteRepository: QuoteRepository = ServiceLocator.quoteRepository

    val quotes: LiveData<List<Quote>> =
        Transformations.map(quoteRepository.getQuotes()) { list ->
            list.map { Quote(author = it.author, quote = it.quote) }
        }

    fun refreshQuotes() {
        viewModelScope.launch {
            quoteRepository.refreshQuotes()
        }
    }
}