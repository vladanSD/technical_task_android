package com.vladan.technical_task_android.repository

import com.vladan.technical_task_android.model.User
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun getUsers(@HeaderMap headers: Map<String, String>): List<User>

    @DELETE("users/{id}")
    suspend fun deleteUser(@HeaderMap headers: Map<String, String>, @Path(value = "id", encoded = true) id: Int): Response<Unit>
}