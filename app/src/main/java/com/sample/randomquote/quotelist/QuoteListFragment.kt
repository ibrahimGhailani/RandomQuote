package com.sample.randomquote.quotelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.sample.randomquote.QuoteGenerator
import com.sample.randomquote.R
import com.sample.randomquote.quotedetails.QuoteDetailsFragment
import com.sample.randomquote.quotedetails.QuoteDetailsFragment.Companion.ARG_QUOTE

class QuoteListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_quote_list, container, false)

        return view.findViewById<RecyclerView>(R.id.list).apply {
            layoutManager = LinearLayoutManager(context)
            adapter = QuoteRecyclerViewAdapter(QuoteGenerator.quotes) { quote ->
                val bundle = Bundle().apply {
                    putParcelable(ARG_QUOTE, quote)
                }
                findNavController().navigate(
                    R.id.action_quoteListFragment_to_quoteDetailsFragment,
                    bundle
                )
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = QuoteListFragment()
    }
}