package com.dashaoao.chat.domain.model

data class Message (
    val id: String,
    val chat: Chat,
    val from: User,
    val text: String
)
