package com.dashaoao.chat.presentation.mvp

import com.dashaoao.api.utils.ResultState
import com.dashaoao.chat.domain.usecase.GetMessagesUseCase
import com.dashaoao.chat.domain.usecase.SendMessageUseCase
import com.dashaoao.common.MVPPresenterBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatPresenter(
    private val getMessagesUseCase: GetMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    private val userId: String
) : MVPPresenterBase<ChatContract.View>(), ChatContract.Presenter {

    override fun getMessages() {
        CoroutineScope(Dispatchers.IO).launch {
            val messages = getMessagesUseCase.invoke(userId)
            withContext(Dispatchers.Main) {
                messages.collect { state ->
                    when (state) {
                        is ResultState.Success -> view?.setMessages(state.data)
                        is ResultState.Loading -> view?.showLoadingState()
                        is ResultState.Error -> view?.showErrorState()
                    }
                }
            }
        }
    }

    override fun sendMessage(message: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val messages = sendMessageUseCase.invoke(userId, message)
            withContext(Dispatchers.Main) {
                messages.collect { state ->
                    when (state) {
                        is ResultState.Success -> Unit
                        is ResultState.Loading -> view?.showLoadingState()
                        is ResultState.Error -> view?.showErrorState()
                    }
                }
            }
        }
    }
}
