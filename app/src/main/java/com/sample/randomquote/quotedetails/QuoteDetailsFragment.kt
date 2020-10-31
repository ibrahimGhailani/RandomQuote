package com.sample.randomquote.quotedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sample.randomquote.Quote
import com.sample.randomquote.R

class QuoteDetailsFragment : BottomSheetDialogFragment() {

    lateinit var quote: Quote
    lateinit var quoteAuthorTextView: TextView
    lateinit var quoteTextView: TextView
    private val args: QuoteDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quote_details, container, false)
        quoteAuthorTextView = view.findViewById(R.id.quote_author)
        quoteTextView = view.findViewById(R.id.quote)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.quote = args.quote

        quoteTextView.text = quote.quote
        quoteAuthorTextView.text = quote.author
    }
}