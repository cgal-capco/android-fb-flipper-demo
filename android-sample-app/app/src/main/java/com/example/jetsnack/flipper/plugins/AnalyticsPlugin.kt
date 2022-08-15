package com.example.jetsnack.flipper.plugins

import android.util.Log
import com.facebook.flipper.core.FlipperConnection
import com.facebook.flipper.core.FlipperObject
import com.facebook.flipper.core.FlipperPlugin
import org.threeten.bp.OffsetDateTime

// for simplicity for this demo, make this plugin singleton
object AnalyticsPlugin : FlipperPlugin {
    private var connection: FlipperConnection? = null

    override fun getId() = "analytics-demo"

    override fun onConnect(connection: FlipperConnection?) {
        this.connection = connection
    }

    override fun onDisconnect() {
        connection = null
    }

    override fun runInBackground(): Boolean = false

    fun trackPage(pageName: String) {
        sendFlipperObject("page-view", pageName)
    }

    fun trackEvent(event: String) {
        sendFlipperObject("event", event)
    }

    private fun sendFlipperObject(type: String, value: String) {
        val now = OffsetDateTime.now()
        val flipperData = FlipperObject.Builder()
            .put("timestamp", now.toString())
            .put("type", type)
            .put("value", value)
            .build()
        connection?.send("newData", flipperData)
        Log.d("FLIPPER", "Sending message to Flipper: ${flipperData.toJsonString()}")
    }
}