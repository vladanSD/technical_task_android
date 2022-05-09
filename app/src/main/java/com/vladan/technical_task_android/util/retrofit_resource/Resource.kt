package com.vladan.technical_task_android.util.retrofit_resource

import com.vladan.technical_task_android.util.retrofit_resource.Status.*

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class Resource<out T, out E> private constructor(val status: Status, val data: T?, val error: E?, val exception: Exception?) {
    companion object {
        fun <T, E> success(data: T) = Resource(SUCCESS, data, null, null)
        fun <T, E> loading(data: T?) = Resource(LOADING, data, null, null)
        fun <T, E> error(data: T?, error: E?, exception: Exception) = Resource(ERROR, data, error, exception)
    }
}