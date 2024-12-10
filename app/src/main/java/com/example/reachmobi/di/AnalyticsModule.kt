package com.example.reachmobi.di

import android.app.Application
import com.example.reachmobi.analytics.AnalyticsLogger
import com.example.reachmobi.analytics.AnalyticsLoggerImpl
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AnalyticsModule {
    @Provides
    fun provideFirebaseAnalytics(application: Application): FirebaseAnalytics {
        val firebaseAnalytics = FirebaseAnalytics.getInstance(application)
        firebaseAnalytics.setAnalyticsCollectionEnabled(true)
        return firebaseAnalytics
    }

    @Singleton
    @Provides
    fun provideAnalyticsLogger(firebaseAnalytics: FirebaseAnalytics): AnalyticsLogger {
        return AnalyticsLoggerImpl(firebaseAnalytics)
    }
}