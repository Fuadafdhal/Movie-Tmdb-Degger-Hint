package com.afdhal_fa.movietmdbdeggerhint.data.services

import com.afdhal_fa.movietmdbdeggerhint.BuildConfig
import com.afdhal_fa.movietmdbdeggerhint.data.response.UserDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

/**
 * Created by Muh Fuad Afdhal on 11/30/2021
 * Email: fuad.afdal.fa@gmail.com
 */

interface AccountServices {
    @GET("/users/{username}")
    suspend fun fetchUserDetail(
        @Path("username") username: String,
        @Header("Authorization") token: String = BuildConfig.API_KEY
    ): UserDto
}
