package com.sample.randomquote.createquote

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.sample.randomquote.R

class AddAuthorFragment : Fragment() {

    lateinit var authorEditText: EditText
    lateinit var nextButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_author, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authorEditText = view.findViewById(R.id.quote_author_input)
        nextButton = view.findViewById(R.id.next_button)

        nextButton.setOnClickListener {
            findNavController().navigate(
                AddAuthorFragmentDirections.actionAddAuthorFragmentToAddQuoteFragment(
                    author = authorEditText.text.toString()
                )
            )
        }
    }
}