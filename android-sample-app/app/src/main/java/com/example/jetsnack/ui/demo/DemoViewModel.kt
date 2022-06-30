package com.example.jetsnack.ui.demo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.jetsnack.core.AppSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class DemoViewModel @Inject constructor(
    private val appSettings: AppSettings
) : ViewModel() {
    fun resetAppSettings() {
        appSettings.counter = 0
        appSettings.isFirstTimeOpen = true
        appSettings.lastSyncDate = null
    }

    fun increaseCounter() {
        appSettings.counter = appSettings.counter + 1
    }

    fun decreaseCounter() {
        appSettings.counter = appSettings.counter - 1
    }

    fun toggleFirstTimeOpen() {
        appSettings.isFirstTimeOpen = !appSettings.isFirstTimeOpen
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun sync() {
        val dateTime = OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
        appSettings.lastSyncDate = dateTime
    }
}