package com.example.jetsnack.network

import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{user}/repos")
    suspend fun listRepos(@Path("user") user: String): List<Repo>
}


data class Repo(
    val id: Int,
    val name: String,
    val html_url: String,
    val description: String,
)