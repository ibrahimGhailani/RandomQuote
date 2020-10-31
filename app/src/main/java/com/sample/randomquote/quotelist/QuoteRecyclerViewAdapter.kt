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
    private val values: ArrayList<Quote>
) : RecyclerView.Adapter<QuoteRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_quote_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.quoteAuthor.text = item.author
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val quoteAuthor: TextView = view.findViewById(R.id.quote_author)
    }
}