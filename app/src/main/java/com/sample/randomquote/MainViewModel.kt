package com.sample.randomquote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.concurrent.Executors

class MainViewModel : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _quote = MutableLiveData<Quote>()
    val quote: LiveData<Quote> = _quote

    fun refreshQuote() {
        val quoteGenerator = QuoteGenerator()

        _loading.postValue(true)

        Executors.defaultThreadFactory().newThread {
            quoteGenerator.getRandomQuote(object : QuoteCallback {
                override fun onSuccess(quote: Quote) {
                    _loading.postValue(false)
                    _quote.postValue(quote)
                }

                override fun onFailure(error: Throwable) {
                    _loading.postValue(false)

                    error.printStackTrace()
                }
            })
        }.start()
    }

}