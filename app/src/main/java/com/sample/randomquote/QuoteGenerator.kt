package com.sample.randomquote

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteGenerator {
    suspend fun getRandomQuote(): Quote {
        return withContext(Dispatchers.IO) {
            Thread.sleep(5_000)
            val index = quotes.indices.random()
            quotes[index]
        }
    }

    companion object {
        val quotes = arrayListOf(
            Quote(
                author = "Brian W. Kernighan",
                quote = "Everyone knows that debugging is twice as hard as writing a program in the first place. So if you're as clever as you can be when you write it, how will you ever debug it?"
            ),
            Quote(
                author = "Linus Torvalds",
                quote = "Talk is cheap. Show me the code."
            ),

            Quote(
                author = "Alan J. Perlis",
                quote = "A language that doesn't affect the way you think about programming is not worth knowing."
            ),
            Quote(
                author = "Bill Gates",
                quote = " Measuring programming progress by lines of code is like measuring aircraft building progress by weight."
            ),
            Quote(
                author = "Brian Kernighan",
                quote = "Debugging is twice as hard as writing the code in the first place. Therefore, if you write the code as cleverly as possible, you are, by definition, not smart enough to debug it."
            ),
            Quote(
                author = "Mark Gibbs",
                quote = "No matter how slick the demo is in rehearsal, when you do it in front of a live audience, the probability of a flawless presentation is inversely proportional to the number of people watching, raised to the power of the amount of money involved."
            ),
            Quote(
                author = "E. W. Dijkstra",
                quote = "If debugging is the process of removing software bugs, then programming must be the process of putting them in."
            )
        )
    }
}


@Parcelize
data class Quote(
    val author: String,
    val quote: String
) : Parcelable

