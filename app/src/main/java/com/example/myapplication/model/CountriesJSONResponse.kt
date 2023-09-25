package com.example.myapplication.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountriesJSONResponseItem(
    val capital: String,
    val code: String,
    val currency: Currency,
    val demonym: String,
    val flag: String,
    val language: Language,
    val name: String,
    val region: String
)
@JsonClass(generateAdapter = true)
data class Currency(
    val code: String,
    val name: String,
    val symbol: String
)
@JsonClass(generateAdapter = true)
data class Language(
    val code: String,
    val iso639_2: String,
    val name: String,
    val nativeName: String
)