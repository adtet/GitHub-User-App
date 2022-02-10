package com.example.ajaib_test.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL

object RetrofitClient {
    private const val base_URL = "https://api.github.com/"

    val retrofit = Retrofit.Builder().baseUrl(base_URL).addConverterFactory(GsonConverterFactory.create()).build()

    val api = retrofit.create(Api::class.java)

}