package com.dashaoao.chat.presentation.mvi

sealed class ChatIntent {
    object GetMessages: ChatIntent()
    class SendMessage(val message: String): ChatIntent()
}
