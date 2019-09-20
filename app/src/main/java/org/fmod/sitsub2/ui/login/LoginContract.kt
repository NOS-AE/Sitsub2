package org.fmod.sitsub2.ui.login

import org.fmod.sitsub2.base.BaseContract

interface LoginContract {
    interface Presenter: BaseContract.Presenter {
        fun tryLogin(id: String, pw: String)
    }
    interface View: BaseContract.View {
        fun loginSuccess()
    }
}