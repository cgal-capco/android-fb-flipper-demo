package com.example.jetsnack

import com.example.jetsnack.core.AppSettings
import com.example.jetsnack.core.AppSettingsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindAppSettings(analyticsServiceImpl: AppSettingsImpl): AppSettings
}