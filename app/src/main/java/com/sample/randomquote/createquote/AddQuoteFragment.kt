package com.sample.randomquote.createquote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.sample.randomquote.R

class AddQuoteFragment : Fragment() {
    lateinit var quoteEditText: EditText
    lateinit var nextButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_quote, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quoteEditText = view.findViewById(R.id.quote_input)
        nextButton = view.findViewById(R.id.next_button)

        nextButton.setOnClickListener {
            //TODO: Navigate to [QuoteSummaryFragment] and pass author and quote
        }
    }
}