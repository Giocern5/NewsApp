package com.example.reachmobi.data.model

data class Article(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val articleUrl: String,
    val imgUrl: String,
    val date: String
)

//Adding defaults
fun ArticleData.toArticle(): Article {
    return Article(
        source = Source(
            id = source.id.takeIf { !it.isNullOrEmpty() } ?: "",
            name = source.name.takeIf { !it.isNullOrEmpty() } ?: "Unknown"
        ),
        author = author.takeIf { !it.isNullOrEmpty() } ?: "Unknown Author",
        title = title.takeIf { !it.isNullOrEmpty() } ?: "No Title Available",
        description = description.takeIf { !it.isNullOrEmpty() } ?: "No description available",
        articleUrl = articleUrl.takeIf { !it.isNullOrEmpty() } ?: "",
        imgUrl = imgUrl.takeIf { !it.isNullOrEmpty() } ?: "",
        date = date.takeIf { !it.isNullOrEmpty() } ?: ""
    )
}
