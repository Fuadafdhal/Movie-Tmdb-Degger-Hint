package com.afdhal_fa.movietmdbdeggerhint.di.module

import com.afdhal_fa.movietmdbdeggerhint.data.dispatcher.CoroutineDispatcherProvider
import com.afdhal_fa.movietmdbdeggerhint.data.dispatcher.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Muh Fuad Afdhal on 11/30/2021
 * Email: fuad.afdal.fa@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class CoroutineDispatcherModule {

    @Binds
    abstract fun providesDispatcher(dispatcherProvider: CoroutineDispatcherProvider): DispatcherProvider
}