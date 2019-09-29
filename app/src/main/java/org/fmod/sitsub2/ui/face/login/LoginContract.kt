package org.fmod.sitsub2.ui.face.login

import org.fmod.sitsub2.base.BaseContract
import org.fmod.sitsub2.data.local.entity.Suggestion
import org.fmod.sitsub2.data.remote.model.recieve.BasicResponse

interface LoginContract {
    interface Presenter: BaseContract.Presenter {
        fun tryLogin(username: String, password: String)
        fun getUserInfo(basicResponse: BasicResponse)
        fun addUserSuggestion(username: String)
        fun getUserSuggestion()
    }
    interface View: BaseContract.View {
        fun loginSuccess()
        fun unauthorized()
        fun loginFail()
        fun getBasicResponseSuccess(basicResponse: BasicResponse)
        fun onGetUserName(username: ArrayList<Suggestion>)
    }
}