package com.vladan.technical_task_android.repository.resource

import com.vladan.technical_task_android.model.Error
import com.vladan.technical_task_android.util.retrofit_resource.AbstractNetworkResource
import okhttp3.ResponseBody
import retrofit2.Retrofit
import java.util.*

open class NetworkResource<T>(
    private val retrofit: Retrofit,
    fetch: (suspend (AbstractNetworkResource<T, Error>) -> T)? = null
) : AbstractNetworkResource<T, Error>(fetch) {

    override fun getError(errorBody: ResponseBody): Error? {
        return try {
            retrofit.responseBodyConverter<Error>(Error::class.java, Error::class.java.annotations)
                .convert(errorBody)
        } catch (e: Exception) {
            e.printStackTrace()
            //FirebaseCrashlytics.getInstance().recordException(e.also { it.printStackTrace() })
            null
        }
    }

    override suspend fun headers() = mutableMapOf<String, String>().apply {
        this["Accept"] = "application/json"
        this["Accept-Language"] = Locale.getDefault().language
    }

}