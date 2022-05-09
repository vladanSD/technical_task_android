package com.vladan.technical_task_android.repository

import com.vladan.technical_task_android.model.User
import com.vladan.technical_task_android.model.UserRequest
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("users")
    suspend fun getUsers(@HeaderMap headers: Map<String, String>): List<User>

    @POST("users")
    suspend fun createUser(@HeaderMap headers: Map<String, String>, @Body userRequest: UserRequest): User

    @DELETE("users/{id}")
    suspend fun deleteUser(@HeaderMap headers: Map<String, String>, @Path(value = "id", encoded = true) id: Int): Response<Unit>
}