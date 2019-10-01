package org.fmod.sitsub2.ui.face.login

import android.net.Uri
import org.fmod.sitsub2.base.BaseContract
import org.fmod.sitsub2.data.local.entity.Suggestion
import org.fmod.sitsub2.data.remote.model.recieve.BasicResponse

interface LoginContract {
    interface Presenter: BaseContract.Presenter {
        fun tryLogin(username: String, password: String) //basic auth登录，获取access_token
        fun handleOAuth(uri: Uri?) //处理redirect back的数据，获取access_token
        fun getUserInfo(basicResponse: BasicResponse) //通过access_token
        fun getUserSuggestion()
        fun deleteUserSuggestion(suggestion: Suggestion)
    }
    interface View: BaseContract.View {
        fun showProgress()
        fun hideProgress()
        fun authSuccess(basicResponse: BasicResponse)
        fun unauthorized()
        fun getInfoSuccess()
        fun loginFail(msg: String)
        fun onGetUserName(username: ArrayList<Suggestion>)
    }
}