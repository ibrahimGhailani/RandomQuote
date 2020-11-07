package com.sample.randomquote.quotelist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.sample.randomquote.R

class QuoteListFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    private val viewModel: QuoteListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quote_list, container, false)

        recyclerView = view.findViewById(R.id.list)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = QuoteRecyclerViewAdapter { quote ->
                findNavController().navigate(
                    QuoteListFragmentDirections.actionQuoteListFragmentToQuoteDetailsFragment(quote)
                )
            }
        }

        view.findViewById<FloatingActionButton>(R.id.add_button).setOnClickListener {
            viewModel.insertQuote()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeObservers()
    }

    private fun initializeObservers() {
        viewModel.quotes.observe(viewLifecycleOwner, {
            (recyclerView.adapter as QuoteRecyclerViewAdapter).insertQuotes(it)
        })
    }
}