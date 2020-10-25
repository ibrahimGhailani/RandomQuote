package com.sample.randomquote

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    lateinit var refreshButton: Button
    lateinit var progressBar: View
    lateinit var quoteTextView: TextView
    lateinit var quoteAuthorTextView: TextView
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUi()
        viewModel.refreshQuote()
    }

    private fun initUi() {
        refreshButton = findViewById(R.id.refresh)
        quoteTextView = findViewById(R.id.quote)
        quoteAuthorTextView = findViewById(R.id.quoteAuthor)
        progressBar = findViewById(R.id.progressBar)

        refreshButton.setOnClickListener {
            viewModel.refreshQuote()
        }

        viewModel.quote.observe(this, Observer { quote ->
            quoteTextView.text = quote.quote
            quoteAuthorTextView.text = quote.author
        })

        viewModel.loading.observe(this, { loading ->
            progressBar.visibility = if (loading) View.VISIBLE else View.INVISIBLE
            refreshButton.isEnabled = !loading
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.saveState()
        super.onSaveInstanceState(outState)
    }
}