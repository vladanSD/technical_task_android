package com.vladan.technical_task_android.repository

import com.vladan.technical_task_android.model.User
import com.vladan.technical_task_android.repository.resource.NetworkResource
import retrofit2.HttpException
import retrofit2.Retrofit

class UserRepository(private val retrofit: Retrofit, private val api: ApiService) {

    val users = NetworkResource<List<User>>(retrofit) {
        api.getUsers(it.headers())
    }

    fun deleteUser(id: Int) = NetworkResource<Unit>(retrofit) {
        api.deleteUser(it.headers(), id).let {
            if (it.isSuccessful) {
                users.getData()?.let {
                    users.setData(
                        it.toMutableList().apply {
                            this.indexOfFirst { it.id == id }.let { index ->
                                if (index != -1)
                                    removeAt(index)
                            }
                        }.toList()
                    )
                }
                it.body()
            } else throw HttpException(it)
        }
    }
}