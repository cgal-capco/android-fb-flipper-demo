package com.example.jetsnack.core

import com.example.jetsnack.flipper.plugins.AnalyticsPlugin

// Use singleton for now. Dependency Injection is out of scope for this demo
object Analytics {
    fun trackPage(pageName: String) {
        // Track the event in something like Firebase or similar
        // for this demo, just put it in the Flipper plugin
        AnalyticsPlugin.trackPage(pageName)
    }

    fun trackEvent(eventName: String) {
        // Track the event in something like Firebase or similar
        // for this demo, just put it in the Flipper plugin
        AnalyticsPlugin.trackEvent(eventName)
    }
}