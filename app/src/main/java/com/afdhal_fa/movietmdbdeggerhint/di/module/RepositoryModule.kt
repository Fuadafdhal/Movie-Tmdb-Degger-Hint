package com.afdhal_fa.movietmdbdeggerhint.di.module

import com.afdhal_fa.movietmdbdeggerhint.data.repository.AccountRepositoryImpl
import com.afdhal_fa.movietmdbdeggerhint.domain.repository.AccountRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

/**
 * Created by Muh Fuad Afdhal on 11/30/2021
 * Email: fuad.afdal.fa@gmail.com
 */

@Module()
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun providesRepository(repository: AccountRepositoryImpl): AccountRepository
}