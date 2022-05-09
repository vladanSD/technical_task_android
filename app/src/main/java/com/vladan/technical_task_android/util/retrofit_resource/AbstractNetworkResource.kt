package com.vladan.technical_task_android.util.retrofit_resource

import com.vladan.technical_task_android.util.retrofit_resource.util.ControlledRunner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

abstract class AbstractNetworkResource<T, E>(private val fetch: (suspend (AbstractNetworkResource<T, E>) -> T)? = null) {

    private val _data = MutableStateFlow<Resource<T, E>>(Resource.loading<T, E>(null))
    val flow = _data.asStateFlow()

    private val controlledRunner = ControlledRunner<Resource<T, E>>()

    open suspend fun run(refresh: Boolean = false, fetch: (suspend (AbstractNetworkResource<T, E>) -> T)? = null): Resource<T, E> {
        val value = _data.value
        return when {
            value.status == Status.SUCCESS && !refresh -> value
            else -> withContext(Dispatchers.IO) {
                controlledRunner.joinPreviousOrRun {
                    _data.value = (Resource.loading<T, E>(value.data))
                    try {
                        Resource.success<T, E>(
                            process(
                                (this@AbstractNetworkResource.fetch
                                    ?: fetch)!!(this@AbstractNetworkResource)
                            )
                        ).also { _data.value = it }
                    } catch (e: HttpException) {
                        e.printStackTrace()
                        //FirebaseCrashlytics.getInstance().recordException(e.also { it.printStackTrace() })
                        Resource.error<T, E>(
                            value.data,
                            e.response()?.errorBody()?.let { getError(it) },
                            e
                        ).also { _data.value = it }
                    } catch (e: IOException) {
                        e.printStackTrace()
                        Resource.error<T, E>(value.data, null, e).also { _data.value = it }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        //FirebaseCrashlytics.getInstance().recordException(e.also { it.printStackTrace() })
                        Resource.error<T, E>(value.data, null, e).also { _data.value = it }
                    }
                }
            }
        }
    }

    protected open fun process(data: T): T = data

    protected abstract fun getError(errorBody: ResponseBody): E?

    abstract suspend fun headers(): MutableMap<String, String>

    fun getData(): T? = flow.value.data

    fun setData(data: T) {
        _data.value = Resource.success<T, E>(data)
    }

}