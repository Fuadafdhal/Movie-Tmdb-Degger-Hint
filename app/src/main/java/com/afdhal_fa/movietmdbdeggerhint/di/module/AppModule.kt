package com.afdhal_fa.movietmdbdeggerhint.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.afdhal_fa.movietmdbdeggerhint.MyTmdbApp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Muh Fuad Afdhal on 11/30/2021
 * Email: fuad.afdal.fa@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context

//    @Provides
//    fun provideApplications(app: MyTmdbApp): Application = app

    @Provides
    @Singleton
    fun providesSharedPreference(sharedPreferences: SharedPreferences): SharedPreferences.Editor =
        sharedPreferences.edit()
}