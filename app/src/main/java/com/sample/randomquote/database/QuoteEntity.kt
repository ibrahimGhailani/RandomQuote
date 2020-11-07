package com.sample.randomquote.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sample.randomquote.database.QuoteEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val quote: String,
    val author: String
) {
    companion object {
        const val TABLE_NAME = "quote"
    }
}