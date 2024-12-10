package com.example.reachmobi.analytics

import com.google.firebase.analytics.FirebaseAnalytics
import android.os.Bundle
import javax.inject.Inject

class AnalyticsLoggerImpl @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics
) : AnalyticsLogger  {

    private fun logEvent(eventName: String, parameters: Bundle? = null) {
        firebaseAnalytics.logEvent(eventName, parameters)
    }

    override fun logSelectedItem(sourceName: String) {
        val bundle = Bundle().apply {
            putString(FirebaseAnalytics.Event.SELECT_ITEM, sourceName)
        }
        logEvent(sourceName, bundle)
    }

    override fun logSearchQuery(query: String) {
        val bundle = Bundle().apply {
            putString(FirebaseAnalytics.Event.SEARCH, query)
        }
        logEvent(query, bundle)
    }

    override fun logAppOpen() {
        val bundle = Bundle().apply {
            putString(FirebaseAnalytics.Event.APP_OPEN, "app_open")
        }
        logEvent("app_open", bundle)
    }
}
