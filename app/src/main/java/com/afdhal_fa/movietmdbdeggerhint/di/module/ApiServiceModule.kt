package com.afdhal_fa.movietmdbdeggerhint.di.module

import com.afdhal_fa.movietmdbdeggerhint.data.services.AccountServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Muh Fuad Afdhal on 11/30/2021
 * Email: fuad.afdal.fa@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {
    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): AccountServices = retrofit.create(AccountServices::class.java)
}