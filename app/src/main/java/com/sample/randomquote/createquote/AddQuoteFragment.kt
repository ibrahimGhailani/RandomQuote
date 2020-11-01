package com.sample.randomquote.createquote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sample.randomquote.R

class AddQuoteFragment : Fragment() {

    lateinit var quoteEditText: EditText
    lateinit var authorTextView: TextView
    lateinit var nextButton: Button

    private val args: AddQuoteFragmentArgs by navArgs()

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
        authorTextView = view.findViewById(R.id.author_text_view)

        authorTextView.text = args.author

        nextButton.setOnClickListener {
            findNavController().navigate(
                AddQuoteFragmentDirections.actionAddQuoteFragmentToQuoteSummaryFragment(
                    author = args.author,
                    quote = quoteEditText.text.toString()
                )
            )
        }
    }
}