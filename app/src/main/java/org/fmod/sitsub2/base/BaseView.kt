package org.fmod.sitsub2.base

interface BaseView<T: BasePresenter> {
    fun injectPresenter(presenter: T)
}