package com.dashaoao.chat.domain.model

import com.dashaoao.api.model.ChatDto
import com.dashaoao.api.model.MessageDto
import com.dashaoao.api.model.UserDto

fun UserDto.toUser(): User {
    return User(
        id = id,
        name = name
    )
}

fun User.toUserDto(): UserDto {
    return UserDto(
        id = id,
        name = name
    )
}

fun ChatDto.toChat(): Chat {
    return Chat(
        firstUser = firstUser.toUser(),
        secondUser = secondUser.toUser()
    )
}

fun Chat.toChatDto(): ChatDto {
    return ChatDto(
        firstUser = firstUser.toUserDto(),
        secondUser = secondUser.toUserDto()
    )
}

fun MessageDto.toMessage(): Message {
    return Message(
        id = id,
        from = from.toUser(),
        chat = chat.toChat(),
        text = text
    )
}

fun Message.toMessageDto(): MessageDto {
    return MessageDto(
        id = id,
        chat = chat.toChatDto(),
        from = from.toUserDto(),
        text = text
    )
}
