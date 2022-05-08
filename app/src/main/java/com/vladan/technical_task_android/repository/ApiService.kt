package com.vladan.technical_task_android.repository

import com.vladan.technical_task_android.model.User
import retrofit2.http.GET
import retrofit2.http.HeaderMap

interface ApiService {

    @GET("users")
    suspend fun getUsers(@HeaderMap headers: Map<String, String>): List<User>
}