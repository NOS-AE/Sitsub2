package org.fmod.sitsub2.ui.face.login

import android.content.Intent
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import org.fmod.sitsub2.AppOpener

import org.fmod.sitsub2.R
import org.fmod.sitsub2.base.BaseMvpActivity
import org.fmod.sitsub2.data.local.entity.Suggestion
import org.fmod.sitsub2.data.remote.model.recieve.BasicResponse
import org.fmod.sitsub2.ui.adapter.SuggestionAdapter
import org.fmod.sitsub2.util.*
import kotlin.collections.ArrayList

class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginContract.View {

    private lateinit var username: String
    private lateinit var password: String

    override fun authSuccess(basicResponse: BasicResponse) {
        toastInfo("认证成功，正在获取用户信息...")
        mPresenter.getUserInfo(basicResponse)
    }

    override fun getInfoSuccess() {
        toastSuccess("登录成功")
        hideProgress()
    }

    override fun loginFail(msg: String) {
        toastError(msg)
        hideProgress()
    }

    override fun unauthorized() {
        toastError("认证失败")
        hideProgress()
    }

    override fun onGetUserName(username: ArrayList<Suggestion>) {
        SuggestionAdapter(this, username){
            mPresenter.deleteUserSuggestion(it)
        }.run {
            login_id.setAdapter(this)
        }
    }

    override fun getLayoutId() = R.layout.activity_login

    override fun initViews() {

        transparentFullScreen()

        mPresenter.getUserSuggestion()
    }

    override fun setListeners() {
        login_login.setOnClickListener {
            if (!loginCheck())
                return@setOnClickListener
            this.showProgress()
            mPresenter.tryLogin(username, password)
        }

        login_browser.setOnClickListener {
            val url = AppOpener.getOAuth2Url()
            AppOpener.openInBrowser(this, url)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        mPresenter.handleOAuth(intent?.data)
        setIntent(null)
    }


    private fun loginCheck(): Boolean {
        var valid = true
        username = login_id.text.toString()
        password = login_pw.text.toString()
        if(username.isBlank()) {
            login_edit1_layout.error = "用户名不可为空"
            valid = false
        } else {
            login_edit1_layout.isErrorEnabled = false
        }
        if(password.isBlank()) {
            login_edit2_layout.error = "密码不可为空"
            valid = false
        } else {
            login_edit2_layout.isErrorEnabled = false
        }
        return valid
    }

    override fun showProgress() {
        login_login.visibility = View.INVISIBLE
        login_progressbar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        login_login.visibility = View.VISIBLE
        login_progressbar.visibility = View.GONE
    }

}
