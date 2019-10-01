package org.fmod.sitsub2.ui.face.login

import android.net.Uri
import org.fmod.sitsub2.base.BasePresenter
import org.fmod.sitsub2.data.DataManager
import org.fmod.sitsub2.data.local.entity.Suggestion
import org.fmod.sitsub2.data.remote.REDIRECT_URL
import org.fmod.sitsub2.data.remote.RemoteHelper
import org.fmod.sitsub2.data.remote.model.recieve.BasicResponse
import org.fmod.sitsub2.util.*
import kotlin.collections.ArrayList


class LoginPresenter: BasePresenter<LoginContract.View>(), LoginContract.Presenter {

    override fun tryLogin(username: String, password: String) {
        launch({
            val response = RemoteHelper.basicLogin(username, password)
            //TODO 不保存敏感信息
            if (response.isSuccessful) {
                DataManager.username = username
                DataManager.password = password
            }

            val status = response.code()
            if(status == 401) {
                mView.unauthorized()
            } else if(status == 200 || status == 201) {
                val basicToken = response.body()
                if(basicToken == null)
                    mView.unauthorized()
                else {
                    mView.authSuccess(basicToken)
                    addUserSuggestion(username)
                }
            } else {
                mView.unauthorized()
            }
        },{
            remoteErrorLog(it.message)
            mView.loginFail(getErrorTip(it))
        })
    }

    override fun handleOAuth(uri: Uri?) {
        if(uri != null && uri.toString().startsWith(REDIRECT_URL)) {
            val code = uri.getQueryParameter("code")
            val state = uri.getQueryParameter("state")
            if(code == null || state == null)
                mView.unauthorized()
            else {
                mView.showProgress()
                getAccessToken(code, state)
            }
        }
    }

    private fun getAccessToken(code: String, state: String) {
        launch({
            val response = RemoteHelper.getAccessToken(code, state)
            if(response.isSuccessful) {
                val oauth = response.body()
                if(oauth == null) {
                    mView.unauthorized()
                } else {
                    mView.authSuccess(BasicResponse(oauth))
                }
            } else {
                mView.unauthorized()
            }
        })/*, {
            remoteErrorLog(it.message)
            mView.loginFail(getErrorTip(it))
        }*/
    }

    //保存basicLogin的用户名 TODO 不保存敏感信息
    private fun addUserSuggestion(username: String) {
        launch ({
            DataManager.insertUserSuggestion(username)
        }, {
            localErrorLog(it.message)
        })
    }

    override fun getUserSuggestion() {
        launch({
            val res = DataManager.findAllUserSuggestion()
            mView.onGetUserName(ArrayList(res))
        }, {
            localErrorLog(it.message)
        })
    }

    override fun deleteUserSuggestion(suggestion: Suggestion) {
        launch({
            DataManager.deleteSuggestion(suggestion)
        }, {
            localErrorLog(it.message)
        })
    }

    override fun getUserInfo(basicResponse: BasicResponse) {
        launch({

        }, {

        })
    }
}