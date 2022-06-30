package com.example.jetsnack.core

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface AppSettings {
    var lastSyncDate: String?
    var isFirstTimeOpen: Boolean
    var counter: Int
}

class AppSettingsImpl @Inject constructor(
    @ApplicationContext val context: Context
) : AppSettings {
    private val file_name = "fb_flipper_app_settings"

    private val LAST_SYNC_KEY = "lastSync"
    private val IS_FIRST_TIME_OPEN_KEY = "isFirstTimeOpen"
    private val COUNTER_KEY = "counter"

    private val sharedPref = context.getSharedPreferences(file_name, Context.MODE_PRIVATE)
    override var lastSyncDate: String?
        get() = sharedPref.getString(LAST_SYNC_KEY, "")
        set(value) {
            with(sharedPref.edit()) {
                putString(LAST_SYNC_KEY, value)
                apply()
            }
        }

    override var isFirstTimeOpen: Boolean
        get() = sharedPref.getBoolean(IS_FIRST_TIME_OPEN_KEY, false)
        set(value) {
            with(sharedPref.edit()) {
                putBoolean(IS_FIRST_TIME_OPEN_KEY, value)
                apply()
            }
        }

    override var counter: Int
        get() = sharedPref.getInt(COUNTER_KEY, 0)
        set(value) {
            with(sharedPref.edit()) {
                putInt(COUNTER_KEY, value)
                apply()
            }
        }
}