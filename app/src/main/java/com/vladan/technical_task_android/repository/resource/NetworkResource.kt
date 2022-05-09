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
        //hardcoded token since there is no auth logic
        //otherwise prefs (for holding token/refresh_token) and authenticator (for refreshing token) should be implemented
        this["Authorization"] = "Bearer 9b08361b7744bb68c04338a4ec58e5ecc02f4395cbfb6f1d5ada7b3f24d494a8"
    }

}