package com.example.jetsnack.network

import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.facebook.flipper.plugins.network.NetworkFlipperPlugin
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesFlipperNetworkPlugin(): NetworkFlipperPlugin {
        return NetworkFlipperPlugin()
    }

    @Singleton
    @Provides
    fun providesHttpClient(networkFlipperPlugin: NetworkFlipperPlugin): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(FlipperOkhttpInterceptor(networkFlipperPlugin))
            .build()
    }

    @Provides
    fun provideGithubService(
        okHttpClient: OkHttpClient
    ): GithubService {
        val client = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        return client.create(GithubService::class.java)
    }
}