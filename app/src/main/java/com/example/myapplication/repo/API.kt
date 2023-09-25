package com.example.myapplication.repo

import com.example.myapplication.model.CountriesJSONResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface API {
    @GET("countries.json")
    suspend fun getCountries(): Response<List<CountriesJSONResponseItem>>
}