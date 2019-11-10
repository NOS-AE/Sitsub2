package org.fmod.sitsub2.ui.activity.login

import android.net.Uri
import org.fmod.sitsub2.AppData
import org.fmod.sitsub2.base.BasePresenter
import org.fmod.sitsub2.data.DataManager
import org.fmod.sitsub2.data.local.entity.AuthUser
import org.fmod.sitsub2.data.local.entity.UserSuggestion
import org.fmod.sitsub2.data.remote.REDIRECT_URL
import org.fmod.sitsub2.data.remote.RemoteHelper
import org.fmod.sitsub2.data.remote.model.recieve.BasicResponse
import org.fmod.sitsub2.data.remote.model.recieve.User
import org.fmod.sitsub2.util.*
import java.util.*


class LoginPresenter: BasePresenter<LoginContract.View>(), LoginContract.Presenter {

    override fun tryLogin(username: String, password: String) {
        launch({
            val response = RemoteHelper.basicLogin(username, password)

            val status = response.code()
            if(status == 401) {
                mView.unauthorized()
            } else if(status == 200 || status == 201) {
                val basicToken = response.body()
                if(basicToken == null)
                    mView.unauthorized()
                else {
                    mView.onAuthSuccess(basicToken)
                    addUserSuggestion(username)
                }
            } else {
                mView.unauthorized()
            }
        },{
            remoteErrorLog(it)
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
                    mView.onAuthSuccess(BasicResponse(oauth))
                }
            } else {
                mView.unauthorized()
            }
        }, {
            remoteErrorLog(it)
            mView.loginFail(getErrorTip(it))
        })
    }

    override fun getUserInfo(basicResponse: BasicResponse) {
        launch({
            val res = RemoteHelper.getUserInfo(basicResponse.accessToken)
            saveAuthUser(basicResponse, res.body() as User)
            mView.onLoginSuccess()
        }, {
            remoteErrorLog(it)
            mView.loginFail(getErrorTip(it))
        })
    }

    private suspend fun saveAuthUser(basicResponse: BasicResponse, user: User) {
        DataManager.authUserDao.updateToUnselected()
        DataManager.authUserDao.deleteByLoginId(user.login)

        val authUser = AuthUser(
            basicResponse.accessToken,
            Date(),
            360 * 24 * 60 * 60,
            basicResponse.scopes.toString(","),
            true,
            user.login,
            user.name,
            user.avatarUrl
        )

        DataManager.authUserDao.insertReplace(authUser)
        AppData.authUser = authUser
        AppData.loggedUser = user
    }

    //保存basicLogin的用户名
    private fun addUserSuggestion(username: String) {
        launch ({
            DataManager.userSuggestionDao.insertReplace(UserSuggestion(username))
        }, {
            localErrorLog(it)
        })
    }

    override fun getUserSuggestion() {
        launch({
            val res = DataManager.userSuggestionDao.find()
            mView.onGetUserName(res)
        }, {
            localErrorLog(it)
        })
    }

    override fun deleteUserSuggestion(userSuggestion: UserSuggestion) {
        launch({
            DataManager.userSuggestionDao.deleteSuggestion(userSuggestion)
        }, {
            localErrorLog(it)
        })
    }

}