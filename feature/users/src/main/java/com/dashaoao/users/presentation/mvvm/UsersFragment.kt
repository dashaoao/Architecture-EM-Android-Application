package com.dashaoao.users.presentation.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.dashaoao.api.utils.ResultState
import com.dashaoao.users.databinding.FragmentUsersBinding
import kotlinx.coroutines.launch

class UsersFragment : Fragment() {

    private var binding: FragmentUsersBinding? = null
    private lateinit var viewModel: UsersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()

        binding?.refreshMvvm?.setOnClickListener {
            viewModel.getUsers()
        }
    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.users.collect { resultState ->
                when (resultState) {
                    is ResultState.Success -> TODO()
                    is ResultState.Loading -> TODO()
                    is ResultState.Error -> TODO()
                }
            }
        }
    }

}
