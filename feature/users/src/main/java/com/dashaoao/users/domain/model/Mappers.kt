package com.dashaoao.users.domain.model

import com.dashaoao.api.model.UserDto

fun UserDto.toUser(): User {
    return User(
        id = id,
        name = name
    )
}
