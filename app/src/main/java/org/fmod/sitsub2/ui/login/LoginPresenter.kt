package org.fmod.sitsub2.ui.login

import org.fmod.sitsub2.base.BasePresenter
import org.fmod.sitsub2.data.remote.RemoteHelper
import org.fmod.sitsub2.util.remoteLog


class LoginPresenter(view: LoginContract.View): BasePresenter<LoginContract.View>(), LoginContract.Presenter {

    init {
        mView = view
    }

    override fun tryLogin(id: String, pw: String) {
        launch(tryBlock = {
            RemoteHelper.basicLogin(id, pw)
        }, catchBlock = {
            remoteLog("basicLogin fail")
        })

    }
}