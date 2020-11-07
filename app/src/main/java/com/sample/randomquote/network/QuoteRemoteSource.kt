package com.sample.randomquote.network

import com.google.gson.annotations.SerializedName
import retrofit2.Response
import retrofit2.http.GET

interface QuoteRemoteSource {
    @GET("quotes/lang/en")
    suspend fun refreshQuotes(): Response<List<QuoteDto>>
}

data class QuoteDto(
    val id: String,
    val author: String,
    @SerializedName("en")
    val quote: String,
    val rating: Double? = null
)