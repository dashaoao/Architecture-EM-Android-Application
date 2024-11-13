package com.dashaoao.chat.presentation.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dashaoao.api.utils.ResultState
import com.dashaoao.chat.domain.model.Message
import com.dashaoao.chat.domain.usecase.GetMessagesUseCase
import com.dashaoao.chat.domain.usecase.SendMessageUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatMVIViewModel(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessagesUseCase: GetMessagesUseCase,
    private val userId: String
) : ViewModel() {

    private val _messages = MutableStateFlow<ResultState<List<Message>>>(ResultState.Loading())
    val messages: StateFlow<ResultState<List<Message>>> = _messages

    init {
        getMessages()
    }

    fun onAction(action: ChatIntent) {
        when (action) {
            is ChatIntent.GetMessages -> getMessages()
            is ChatIntent.SendMessage -> sendMessage(action.message)
        }
    }

    private fun getMessages() {
        viewModelScope.launch {
            getMessagesUseCase.invoke(userId).collect {
                _messages.value = it
            }
        }
    }

    private fun sendMessage(message: String) {
        viewModelScope.launch {
            sendMessageUseCase.invoke(userId, message)
        }
    }
}
