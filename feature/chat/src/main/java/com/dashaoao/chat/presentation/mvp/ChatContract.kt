package com.dashaoao.chat.presentation.mvp

import com.dashaoao.chat.domain.model.Message
import com.dashaoao.common.MVPPresenter
import com.dashaoao.common.MVPView

interface ChatContract {
    interface View : MVPView {
        fun showLoadingState()
        fun showErrorState()
        fun setMessages(messages: List<Message>)
    }

    interface Presenter : MVPPresenter<View> {
        fun getMessages()
        fun sendMessage(message: String)
    }
}
