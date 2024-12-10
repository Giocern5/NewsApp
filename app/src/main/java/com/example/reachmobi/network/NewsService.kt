package com.example.reachmobi.network

import com.example.reachmobi.data.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

private const val API_KEY = "f782939f1a30483e988fd2d52b17342d"

interface NewsService {

    @GET("top-headlines?country=us&apiKey=$API_KEY")
    suspend fun getNews(
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int
    ): Response<NewsResponse>

    @GET("everything?apiKey=$API_KEY")
    suspend fun searchNews(
        @Query("q") q: String,
        @Query("pageSize") pageSize: Int,
        @Query("page") page: Int,
        @Query("sortBy") sortBy: String = "relevancy" // defaulted but other types can be added
        ): Response<NewsResponse>

}