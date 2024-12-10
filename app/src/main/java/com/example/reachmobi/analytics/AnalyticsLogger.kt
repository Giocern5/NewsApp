package com.example.reachmobi.analytics

interface AnalyticsLogger {
    fun logSelectedItem(sourceName: String)
    fun logSearchQuery(query: String)
    fun logAppOpen()
}