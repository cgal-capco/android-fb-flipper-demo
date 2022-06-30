package com.example.jetsnack

import android.content.Context
import com.example.jetsnack.core.AppSettings
import com.example.jetsnack.core.AppSettingsImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesAppSettings(@ApplicationContext applicationContext: Context): AppSettings {
        return AppSettingsImpl(applicationContext)
    }
}