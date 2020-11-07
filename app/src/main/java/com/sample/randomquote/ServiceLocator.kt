package com.sample.randomquote

import android.content.Context
import androidx.room.Room
import com.sample.randomquote.database.QuoteDatabase
import com.sample.randomquote.network.QuoteRemoteSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceLocator {
    private lateinit var app: App
    lateinit var db: QuoteDatabase
    lateinit var retrofit: Retrofit
    private lateinit var quoteRemoteSource: QuoteRemoteSource

    fun init(app: App) {
        this.app = app
        initializeDatabase(app)
        initializeNetwork(app)
    }

    private fun getOkhttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private fun initializeNetwork(context: Context) {
        retrofit = Retrofit.Builder()
            .baseUrl("https://programming-quotes-api.herokuapp.com/")
            .client(getOkhttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        quoteRemoteSource = retrofit.create(QuoteRemoteSource::class.java)
    }

    private fun initializeDatabase(context: Context) {
        db = Room.databaseBuilder(
            context,
            QuoteDatabase::class.java,
            "quote_db"
        ).fallbackToDestructiveMigration().build()
    }

    val quoteRepository: QuoteRepository by lazy {
        QuoteRepository(db.quoteDao(), quoteRemoteSource)
    }
}