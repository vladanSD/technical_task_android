package com.vladan.technical_task_android.di

import com.vladan.technical_task_android.ui.home.validations.ValidateEmail
import com.vladan.technical_task_android.ui.home.validations.ValidateName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ValidationsModule {

    @Provides
    @Singleton
    fun provideValidateName() = ValidateName()

    @Provides
    @Singleton
    fun provideValidateEmail() = ValidateEmail()
}