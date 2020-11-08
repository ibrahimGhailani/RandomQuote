package com.sample.randomquote

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sample.randomquote.database.QuoteDatabase
import com.sample.randomquote.database.QuoteEntity
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
    val quoteRepository: QuoteRepository by lazy {
        QuoteRepository(db.quoteDao(), quoteRemoteSource)
    }

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
        ).addMigrations(MIGRATION_1_2).build()
    }


    private val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE ${QuoteEntity.TABLE_NAME} ADD COLUMN rating REAL DEFAULT null")
        }
    }

}