package org.fmod.sitsub2.base

abstract class BasePresenter<T: BaseContract.View>: BaseContract.Presenter {
    protected lateinit var mView: T
}