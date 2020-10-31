package com.sample.randomquote.quotedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sample.randomquote.Quote
import com.sample.randomquote.R

class QuoteDetailsFragment : BottomSheetDialogFragment() {

    lateinit var quote: Quote
    lateinit var quoteAuthorTextView: TextView
    lateinit var quoteTextView: TextView

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

        arguments?.let {
            it.getParcelable<Quote>(ARG_QUOTE)?.let { quote ->
                this.quote = quote
            }
        }

        quoteTextView.text = quote.quote
        quoteAuthorTextView.text = quote.author
    }

    companion object {
        const val ARG_QUOTE = "quote"

        fun newInstance(quote: Quote): QuoteDetailsFragment {
            return QuoteDetailsFragment().apply {
                val bundle = Bundle()
                bundle.putParcelable(ARG_QUOTE, quote)
                arguments = bundle
            }
        }
    }
}