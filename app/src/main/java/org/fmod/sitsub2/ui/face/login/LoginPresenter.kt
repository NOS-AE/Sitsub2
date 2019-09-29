package org.fmod.sitsub2.ui.face.login

import org.fmod.sitsub2.base.BasePresenter
import org.fmod.sitsub2.data.DataManager
import org.fmod.sitsub2.data.remote.RemoteHelper
import org.fmod.sitsub2.data.remote.model.recieve.BasicResponse
import org.fmod.sitsub2.util.toastError
import org.fmod.sitsub2.util.toastWarning


class LoginPresenter(view: LoginContract.View): BasePresenter<LoginContract.View>(), LoginContract.Presenter {

    init {
        mView = view
    }

    override fun tryLogin(username: String, password: String) {
        launch {
            val response = RemoteHelper.basicLogin(username, password)
            if(response.isSuccessful){
                DataManager.username = username
                DataManager.password = password
            }

            when(response.code()){
                401 -> {
                    toastWarning("账号或密码错误")
                }
                200 -> { //first create
                    mView.loginSuccess()
                }
                201 -> { //created
                    mView.loginSuccess()
                }
                else -> toastError("请检查网络")
            }
        }
    }

    override fun getUserInfo(basicResponse: BasicResponse) {
        launch {
            
        }
    }
}