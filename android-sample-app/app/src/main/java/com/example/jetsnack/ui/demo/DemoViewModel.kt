package com.example.jetsnack.ui.demo

import android.os.Build
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetsnack.core.AppSettings
import com.example.jetsnack.database.daos.ProductDao
import com.example.jetsnack.database.daos.UserDao
import com.example.jetsnack.database.entities.ProductEntity
import com.example.jetsnack.database.entities.UserEntity
import com.example.jetsnack.network.GithubService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class DemoViewModel @Inject constructor(
    private val appSettings: AppSettings,
    private val userDao: UserDao,
    private val productDao: ProductDao,
    private val githubService: GithubService
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

    fun sync() {
        val dateTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            OffsetDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
        } else {
            "Android version not supported"
        }
        appSettings.lastSyncDate = dateTime
    }

    fun crashApp() {
        viewModelScope.launch {
            // Will crash because it's not launched from the correct IO dispatcher
//            userDao.clearTable()

            withContext(Dispatchers.IO) {
                userDao.clearTable()
            }
        }
    }

    fun seedSqlDatabase() {
        viewModelScope.launch {
            seedDatabase()
        }
    }

    private suspend fun seedDatabase() = withContext(Dispatchers.IO) {
        userDao.clearTable()
        userDao.insertAll(
            UserEntity(firstName = "Luke", lastName = "Skywalker"),
            UserEntity(firstName = "Oki-wan", lastName = "Kenobi"),
            UserEntity(firstName = "Han", lastName = "Solo"),
        )

        productDao.clearTable()
        productDao.insertAll(
            ProductEntity(title = "Cake", description = "Chocolate cake"),
            ProductEntity(title = "Cookie", description = "Chocolate chips cookie"),
            ProductEntity(title = "Donut", description = "A not very healthy donut"),
        )
    }


    fun searchGithub(userName: String) {
        viewModelScope.launch {
            Log.i("NETWORK", "Getting repos for $userName")
            val results = githubService.listRepos(userName)
        }
    }
}