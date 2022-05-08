package com.vladan.technical_task_android.repository

import com.vladan.technical_task_android.model.User
import com.vladan.technical_task_android.repository.resource.NetworkResource
import retrofit2.Retrofit

class UserRepository(private val retrofit: Retrofit, private val api: ApiService) {

    val users = NetworkResource<List<User>>(retrofit) {
        api.getUsers(it.headers())
    }
}