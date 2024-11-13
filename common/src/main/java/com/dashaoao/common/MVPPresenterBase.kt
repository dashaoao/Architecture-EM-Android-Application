package com.dashaoao.common

abstract class MVPPresenterBase<V: MVPView> : MVPPresenter<V> {
    var view: V? = null
        private set

    override fun attachView(view: V) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }
}
