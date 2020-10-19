package com.sample.randomquote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    lateinit var refreshButton: Button
    lateinit var progressBar: View
    lateinit var quoteTextView: TextView
    lateinit var quoteAuthorTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
        refreshQuote()
    }

    private fun initUi() {
        refreshButton = findViewById(R.id.refresh)
        quoteTextView = findViewById(R.id.quote)
        quoteAuthorTextView = findViewById(R.id.quoteAuthor)
        progressBar = findViewById(R.id.progressBar)

        refreshButton.setOnClickListener {
            refreshQuote()
        }
    }
    /**
     * Get a random quote and display it to the user
     */
    private fun refreshQuote() {
        val quoteGenerator = QuoteGenerator()

        progressBar.visibility = View.VISIBLE
        refreshButton.isEnabled = false

        Executors.defaultThreadFactory().newThread {
            quoteGenerator.getRandomQuote(object : QuoteCallback {
                override fun onSuccess(quote: Quote) {
                    Log.d("MainActivity", "$quote")

                    runOnUiThread {
                        progressBar.visibility = View.INVISIBLE
                        refreshButton.isEnabled = true

                        quoteTextView.text = quote.quote
                        quoteAuthorTextView.text = quote.author
                    }
                }

                override fun onFailure(error: Throwable) {
                    progressBar.visibility = View.INVISIBLE
                    refreshButton.isEnabled = true

                    error.printStackTrace()
                }

            })
        }.start()
    }
}