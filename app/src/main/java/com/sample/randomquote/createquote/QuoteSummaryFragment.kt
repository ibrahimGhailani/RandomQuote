package com.sample.randomquote.createquote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.sample.randomquote.Quote
import com.sample.randomquote.R

class QuoteSummaryFragment : Fragment() {

    private lateinit var authorTextView: TextView
    private lateinit var quoteTextView: TextView
    private lateinit var saveButton: Button
    private lateinit var quote: Quote

    private val args: QuoteSummaryFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quote_summary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        quoteTextView = view.findViewById(R.id.quote)
        authorTextView = view.findViewById(R.id.quote_author)
        saveButton = view.findViewById(R.id.save_button)

        quote = Quote(
            author = args.author,
            quote = args.quote
        )

        populateUi()

    }

    private fun populateUi() {
        quoteTextView.text = quote.quote
        authorTextView.text = quote.author
    }
}