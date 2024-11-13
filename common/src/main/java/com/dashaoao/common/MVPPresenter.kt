package com.dashaoao.common

interface MVPPresenter <V : MVPView> {
    fun attachView(view: V)
    fun detachView()
}
