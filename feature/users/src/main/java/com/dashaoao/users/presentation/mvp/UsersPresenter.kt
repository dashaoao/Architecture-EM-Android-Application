package com.dashaoao.users.presentation.mvp

import com.dashaoao.api.utils.ResultState
import com.dashaoao.users.domain.usecase.GetUsersUseCase
import com.dashaoao.common.MVPPresenterBase
import com.dashaoao.users.presentation.mvp.UsersContract
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsersPresenter(
    private val getUsersUseCase: GetUsersUseCase
): MVPPresenterBase<UsersContract.View>(), UsersContract.Presenter {

    override fun getUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            val resultStateFlow = getUsersUseCase.invoke()

            withContext(Dispatchers.Main) {
                resultStateFlow.collect { state ->
                    when (state) {
                        is ResultState.Loading -> view?.showLoadingState()
                        is ResultState.Error -> view?.showErrorState()
                        is ResultState.Success -> view?.setListUsers(state.data)
                    }
                }
            }
        }
    }

}
