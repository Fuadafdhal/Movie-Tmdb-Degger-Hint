package com.afdhal_fa.movietmdbdeggerhint.domain.entity

/**
 * Created by Muh Fuad Afdhal on 11/29/2021
 * Email: fuad.afdal.fa@gmail.com
 */

data class User(
    val bio: String,
    val type: String,
    val blog: String,
    val company: String?,
    val id: Int,
    val email: String?,
    val followers: Int,
    val following: Int,
    val name: String,
    val location: String,
)