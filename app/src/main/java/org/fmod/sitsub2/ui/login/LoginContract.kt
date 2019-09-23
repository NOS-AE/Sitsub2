package org.fmod.sitsub2.ui.login

import org.fmod.sitsub2.base.BaseContract
import org.fmod.sitsub2.data.remote.model.recieve.BasicResponse

interface LoginContract {
    interface Presenter: BaseContract.Presenter {
        fun tryLogin(username: String, password: String)
        fun getUserInfo(basicResponse: BasicResponse)
    }
    interface View: BaseContract.View {
        fun loginSuccess()
        fun getBasicResponseSuccess(basicResponse: BasicResponse)
    }
}