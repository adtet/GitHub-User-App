package com.example.ajaib_test.api

import com.example.ajaib_test.model.reponseGetDetailUser
import com.example.ajaib_test.model.reponseGetSearchUser
import com.example.ajaib_test.model.responseGetUserRepos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
//    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query:String
    ):Call<reponseGetSearchUser>

    @GET("users/{login}")
//    @Headers("Authorization: token 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")
    fun getDetailUser(
            @Path("login") login:String
    ):Call<reponseGetDetailUser>

    @GET("/users/{login}/repos")
    fun getListRepos(
        @Path("login") login: String
    ):Call<List<responseGetUserRepos>>



}