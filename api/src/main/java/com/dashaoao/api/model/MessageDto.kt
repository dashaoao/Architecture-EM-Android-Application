package com.dashaoao.api.model

data class MessageDto (
    val id: String,
    val chat: ChatDto,
    val from: UserDto,
    val text: String
)
