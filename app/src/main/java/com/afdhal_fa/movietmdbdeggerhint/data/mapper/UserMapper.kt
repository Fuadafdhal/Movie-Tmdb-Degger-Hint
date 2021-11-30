package com.afdhal_fa.movietmdbdeggerhint.data.mapper

import com.afdhal_fa.movietmdbdeggerhint.abstraction.Mapper
import com.afdhal_fa.movietmdbdeggerhint.data.response.UserDto
import com.afdhal_fa.movietmdbdeggerhint.domain.entity.User
import javax.inject.Inject

/**
 * Created by Muh Fuad Afdhal on 11/30/2021
 * Email: fuad.afdal.fa@gmail.com
 */

class UserMapper @Inject constructor() : Mapper<UserDto, User> {
    override fun map(input: UserDto): User {
        return User(
            bio = input.bio,
            type = input.type,
            blog = input.blog,
            company = input.company,
            id = input.id,
            email = input.email,
            followers = input.followers,
            following = input.following,
            name = input.name,
            location = input.location
        )
    }

}