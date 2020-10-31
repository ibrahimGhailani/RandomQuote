package com.sample.randomquote.quotelist

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sample.randomquote.Quote
import com.sample.randomquote.R

/**
 * [RecyclerView.Adapter] that can display a [Quote].
 */
class QuoteRecyclerViewAdapter(
    private val quotes: ArrayList<Quote>,
    private val doOnItemClicked: (Quote) -> Unit
) : RecyclerView.Adapter<QuoteRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_quote_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quote = quotes[position]
        holder.quoteAuthor.text = quote.author
        holder.root.setOnClickListener { doOnItemClicked(quote) }
    }

    override fun getItemCount(): Int = quotes.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root: View = view.findViewById(R.id.root)
        val quoteAuthor: TextView = view.findViewById(R.id.quote_author)
    }
}