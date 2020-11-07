package com.sample.randomquote

import android.content.Context
import androidx.room.Room
import com.sample.randomquote.database.QuoteDatabase

object ServiceLocator {
    private lateinit var app: App
    lateinit var db: QuoteDatabase

    fun init(app: App) {
        this.app = app
        initializeDatabase(app)
    }

    private fun initializeDatabase(context: Context) {
        db = Room.databaseBuilder(
            context,
            QuoteDatabase::class.java,
            "quote_db"
        ).build()
    }

    val quoteRepository: QuoteRepository by lazy {
        QuoteRepository(db.quoteDao())
    }
}