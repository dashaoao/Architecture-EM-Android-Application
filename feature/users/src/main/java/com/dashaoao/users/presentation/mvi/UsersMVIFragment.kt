package com.dashaoao.users.presentation.mvi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.dashaoao.api.utils.ResultState
import com.dashaoao.users.databinding.FragmentUsersMviBinding
import com.dashaoao.users.presentation.mvi.UsersIntent
import com.dashaoao.users.presentation.mvi.UsersMVIViewModel
import kotlinx.coroutines.launch

class UsersMVIFragment : Fragment() {
    private var binding: FragmentUsersMviBinding? = null
    private lateinit var viewModel: UsersMVIViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersMviBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeState()

        binding?.refreshMvi?.setOnClickListener {
            viewModel.onAction(UsersIntent.GetUsers)
        }
    }

    private fun observeState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.users.collect { users ->
                when (users) {
                    is ResultState.Success -> TODO()
                    is ResultState.Loading -> TODO()
                    is ResultState.Error -> TODO()
                }
            }
        }
    }
}
