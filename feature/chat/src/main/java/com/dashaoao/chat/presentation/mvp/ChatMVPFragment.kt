package com.dashaoao.chat.presentation.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dashaoao.chat.databinding.FragmentChatMvpBinding
import com.dashaoao.chat.domain.model.Message

class ChatMVPFragment : Fragment(), ChatContract.View {

    private var binding: FragmentChatMvpBinding?= null
    private lateinit var presenter: ChatPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatMvpBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.getMessages()

        binding?.messageField?.setOnClickListener {
            presenter.sendMessage(binding?.messageField?.text.toString())
            presenter.getMessages()
        }
    }

    override fun showLoadingState() {
        TODO()
    }

    override fun showErrorState() {
        TODO()
    }

    override fun setMessages(messages: List<Message>) {
        TODO()
    }
}
