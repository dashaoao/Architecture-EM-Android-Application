package com.dashaoao.users.presentation.mvp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dashaoao.users.databinding.FragmentUsersMvpBinding
import com.dashaoao.users.domain.model.User
import com.dashaoao.users.presentation.mvp.UsersContract

class UsersMVPFragment : Fragment(), UsersContract.View {

    private var binging: FragmentUsersMvpBinding? = null
    private lateinit var presenter: UsersContract.Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binging = FragmentUsersMvpBinding.inflate(inflater, container, false)
        return binging!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.getUsers()

        binging?.refreshMvp?.setOnClickListener {
            presenter.getUsers()
        }
    }

    override fun showLoadingState() {
        TODO()
    }

    override fun showErrorState() {
        TODO()
    }

    override fun setListUsers(users: List<User>) {
        TODO()
    }

}
