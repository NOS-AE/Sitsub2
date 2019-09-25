package org.fmod.sitsub2.ui.login

import android.annotation.SuppressLint
import android.view.View
import androidx.lifecycle.ViewModel
import com.jakewharton.rxbinding3.view.clicks
import kotlinx.android.synthetic.main.activity_login.*
import org.fmod.sitsub2.R
import org.fmod.sitsub2.base.BaseActivity
import org.fmod.sitsub2.data.remote.model.recieve.BasicResponse
import org.fmod.sitsub2.util.toastInfo
import org.fmod.sitsub2.util.toastSuccess
import org.fmod.sitsub2.util.transparentFullScreen
import java.util.concurrent.TimeUnit

class LoginActivity : BaseActivity<LoginContract.Presenter>(), LoginContract.View {

    private lateinit var username: String
    private lateinit var password: String

    override fun injectPresenter(): LoginPresenter {
        return LoginPresenter(this)
    }


    override fun loginSuccess() {
        login_login.visibility = View.VISIBLE
        login_progressbar.visibility = View.GONE
        toastSuccess("登录成功")
    }

    override fun getBasicResponseSuccess(basicResponse: BasicResponse) {
        toastInfo("认证成功")
        mPresenter.getUserInfo(basicResponse)
        
    }

    override fun getLayoutId() = R.layout.activity_login

    override fun initViews() {
        transparentFullScreen()
    }


    @SuppressLint("CheckResult")
    override fun setListeners() {

        login_login.clicks()
            .throttleFirst(1, TimeUnit.SECONDS)
            .filter {
                loginCheck()
            }
            .subscribe {
                login_login.visibility = View.INVISIBLE
                login_progressbar.visibility = View.VISIBLE
                mPresenter.tryLogin(username, password)
                /*GlobalScope.launch(Dispatchers.Main) {
                    delay(5000)
                    mPresenter.tryLogin(username, password)
                }*/

            }
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


}
