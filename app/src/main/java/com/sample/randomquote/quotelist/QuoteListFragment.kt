package com.sample.randomquote.quotelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sample.randomquote.QuoteGenerator
import com.sample.randomquote.R

class QuoteListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quote_list, container, false)

        view.findViewById<RecyclerView>(R.id.list).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = QuoteRecyclerViewAdapter(QuoteGenerator.quotes) { quote ->
                findNavController().navigate(
                    QuoteListFragmentDirections.actionQuoteListFragmentToQuoteDetailsFragment(quote)
                )
            }
        }

        view.findViewById<FloatingActionButton>(R.id.add_button).setOnClickListener {
            findNavController().navigate(
                QuoteListFragmentDirections.actionQuoteListFragmentToAddAuthorFragment()
            )
        }
        return view
    }
}