package com.example.jetsnack

import android.app.Application
import android.util.Log
import com.example.jetsnack.flipper.plugins.AnalyticsPlugin
import com.facebook.flipper.android.AndroidFlipperClient
import com.facebook.flipper.plugins.databases.DatabasesFlipperPlugin
import com.facebook.flipper.plugins.inspector.DescriptorMapping
import com.facebook.flipper.plugins.inspector.InspectorFlipperPlugin
import com.facebook.flipper.plugins.leakcanary2.FlipperLeakListener
import com.facebook.flipper.plugins.leakcanary2.LeakCanary2FlipperPlugin
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import com.facebook.flipper.plugins.sharedpreferences.SharedPreferencesFlipperPlugin
import com.facebook.soloader.SoLoader
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp
import leakcanary.LeakCanary
import javax.inject.Inject

@HiltAndroidApp
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)
        configureFlipper()
    }


    @Inject
    lateinit var flipperNetworkPlugin: NetworkFlipperPlugin

    private fun configureFlipper() {
        SoLoader.init(this, false)

//        if (BuildConfig.DEBUG && FlipperUtils.shouldEnableFlipper(this)) {
        Log.i("Flipper", "Initializing Flipper")
        /*
        set the flipper listener in leak canary config
        */
        LeakCanary.config = LeakCanary.config.copy(
            onHeapAnalyzedListener = FlipperLeakListener()
        )

        val client = AndroidFlipperClient.getInstance(this)
        client.addPlugin(InspectorFlipperPlugin(this, DescriptorMapping.withDefaults()))
        client.addPlugin(LeakCanary2FlipperPlugin())
        client.addPlugin(SharedPreferencesFlipperPlugin(this, "fb_flipper_app_settings"))
        client.addPlugin(DatabasesFlipperPlugin(this))
        client.addPlugin(flipperNetworkPlugin)
        client.addPlugin(AnalyticsPlugin)
        client.start()
//        }
    }
}