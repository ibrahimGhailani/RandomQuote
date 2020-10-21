package com.sample.randomquote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import java.util.concurrent.Executors

class MainViewModel(val savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    private val _quote = MutableLiveData<Quote>()
    val quote: LiveData<Quote> = _quote

    fun refreshQuote() {
        if (!loadState()) {
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

    /**
     * Loads the state of the app before activity was reconfigured or destroyed
     *
     * @return: True if the state was loaded. False if the application was not loaded from a
     * previous state
     */
    private fun loadState(): Boolean {
        if (!savedStateHandle.contains(QUOTE) && !savedStateHandle.contains(QUOTE_AUTHOR)){
            return false
        }

        val quote = Quote(
            author = savedStateHandle.get<String>(QUOTE_AUTHOR) ?: "",
            quote = savedStateHandle.get<String>(QUOTE) ?: ""
        )
        savedStateHandle.remove<String>(QUOTE)
        savedStateHandle.remove<String>(QUOTE_AUTHOR)
        _quote.postValue(quote)
        return true
    }

    fun saveState() {
        savedStateHandle.set(QUOTE_AUTHOR, _quote.value?.author)
        savedStateHandle.set(QUOTE, _quote.value?.quote)
    }

    companion object {
        const val QUOTE = "quote"
        const val QUOTE_AUTHOR = "quote_author"
    }
}