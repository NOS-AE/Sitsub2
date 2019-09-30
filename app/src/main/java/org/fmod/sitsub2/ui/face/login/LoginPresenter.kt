package org.fmod.sitsub2.ui.face.login

import org.fmod.sitsub2.base.BasePresenter
import org.fmod.sitsub2.data.DataManager
import org.fmod.sitsub2.data.local.entity.Suggestion
import org.fmod.sitsub2.data.remote.RemoteHelper
import org.fmod.sitsub2.data.remote.model.recieve.BasicResponse
import org.fmod.sitsub2.util.localErrorLog
import org.fmod.sitsub2.util.log


class LoginPresenter: BasePresenter<LoginContract.View>(), LoginContract.Presenter {

    override fun tryLogin(username: String, password: String) {
        launch {
            log("$username, $password\n")
            val response = RemoteHelper.basicLogin(username, password)
            if(response.isSuccessful){
                DataManager.username = username
                DataManager.password = password
            }
            when(response.code()){
                401 -> {
                    mView.unauthorized()
                }
                200 -> { //first create
                    mView.loginSuccess()
                }
                201 -> { //created
                    mView.loginSuccess()
                }
                else -> mView.loginFail()
            }
        }
    }

    override fun addUserSuggestion(username: String) {
        launch (tryBlock = {
            DataManager.insertUserSuggestion(username)
        }, catchBlock = {
            localErrorLog(it.message)
        })
    }

    override fun getUserSuggestion() {
        launch(tryBlock = {
            val res = DataManager.findAllUserSuggestion()
            mView.onGetUserName(ArrayList(res))
        }, catchBlock = {
            localErrorLog(it.message)
        })
    }

    override fun deleteUserSuggestion(suggestion: Suggestion) {
        launch(tryBlock = {
            DataManager.deleteSuggestion(suggestion)
        }, catchBlock = {
            localErrorLog(it.message)
        })
    }

    override fun getUserInfo(basicResponse: BasicResponse) {
        launch {
            
        }
    }
}