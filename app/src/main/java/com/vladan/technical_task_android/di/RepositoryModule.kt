package com.vladan.technical_task_android.di

import com.vladan.technical_task_android.repository.ApiService
import com.vladan.technical_task_android.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(retrofit: Retrofit, api: ApiService) = UserRepository(retrofit, api)
}