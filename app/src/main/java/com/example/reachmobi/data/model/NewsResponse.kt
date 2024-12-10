package com.example.reachmobi.data.model

import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)

data class NewsResponse (
    @SerializedName("status")  val status: String,
    @SerializedName("totalResults") val totalResults: String,
    @SerializedName("articles") val articles: List<ArticleData>
)

data class ArticleData(
    @SerializedName("source") val source: Source,
    @SerializedName("author") val author: String,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("url") val articleUrl: String,
    @SerializedName("urlToImage") val imgUrl: String,
    @SerializedName("publishedAt") val date: String,
    @SerializedName("content") val content: String
)


