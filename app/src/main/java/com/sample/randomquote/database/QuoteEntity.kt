package com.sample.randomquote.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sample.randomquote.database.QuoteEntity.Companion.TABLE_NAME
import java.util.*

@Entity(tableName = TABLE_NAME)
data class QuoteEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val quote: String,
    val author: String,
    @ColumnInfo(defaultValue = "null")
    val rating: Double? = null
) {
    companion object {
        const val TABLE_NAME = "quote"
    }
}