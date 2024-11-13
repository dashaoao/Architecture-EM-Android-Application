package com.dashaoao.users.presentation.mvp

import com.dashaoao.common.MVPPresenter
import com.dashaoao.common.MVPView
import com.dashaoao.users.domain.model.User

interface UsersContract {
    interface View : MVPView {
        fun showLoadingState()
        fun showErrorState()
        fun setListUsers(users: List<User>)
    }

    interface Presenter : MVPPresenter<View> {
        fun getUsers()
    }
}
