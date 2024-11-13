package com.dashaoao.chat.presentation.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.dashaoao.api.utils.ResultState
import com.dashaoao.chat.databinding.FragmentChatBinding
import kotlinx.coroutines.launch

class ChatFragment : Fragment() {

    private var binding: FragmentChatBinding?= null
    private lateinit var viewModel: ChatViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()

        binding?.messageField?.setOnClickListener {
            viewModel.sendMessage(binding?.messageField?.text.toString())
            viewModel.getMessages()
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
