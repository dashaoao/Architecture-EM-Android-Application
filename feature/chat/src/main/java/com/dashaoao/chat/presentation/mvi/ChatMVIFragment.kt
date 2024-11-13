package com.dashaoao.chat.presentation.mvi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.dashaoao.api.utils.ResultState
import com.dashaoao.chat.databinding.FragmentChatMviBinding
import kotlinx.coroutines.launch

class ChatMVIFragment: Fragment() {

    private var binding: FragmentChatMviBinding?= null

    private lateinit var viewModel: ChatMVIViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatMviBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()

        binding?.messageField?.setOnClickListener {
            viewModel.onAction(ChatIntent.SendMessage(binding?.messageField?.text.toString()))
            viewModel.onAction(ChatIntent.GetMessages)
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.messages.collect { state ->
                when (state) {
                    is ResultState.Loading -> TODO()
                    is ResultState.Error -> TODO()
                    is ResultState.Success -> TODO()
                }
            }
        }
    }
}
