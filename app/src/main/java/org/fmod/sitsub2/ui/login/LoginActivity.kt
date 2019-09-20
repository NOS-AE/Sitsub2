package org.fmod.sitsub2.ui.login

import android.annotation.SuppressLint
import com.jakewharton.rxbinding3.view.clicks
import kotlinx.android.synthetic.main.activity_login.*
import org.fmod.sitsub2.R
import org.fmod.sitsub2.base.BaseActivity
import org.fmod.sitsub2.util.toast
import org.fmod.sitsub2.util.transparentFullScreen
import java.util.concurrent.TimeUnit

class LoginActivity : BaseActivity<LoginPresenter>(), LoginContract.View {

    private lateinit var id: String
    private lateinit var pw: String

    override fun injectPresenter(): LoginPresenter {
        return LoginPresenter(this)
    }


    override fun loginSuccess() {
        toast("登录成功")
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
                mPresenter.tryLogin(id, pw)
            }
    }

    private fun loginCheck(): Boolean {
        var valid = true
        id = login_id.text.toString()
        pw = login_pw.text.toString()
        if(id.isBlank()) {
            login_edit1_layout.error = "用户名不可为空"
            valid = false
        } else {
            login_edit1_layout.isErrorEnabled = false
        }
        if(pw.isBlank()) {
            login_edit2_layout.error = "密码不可为空"
            valid = false
        } else {
            login_edit2_layout.isErrorEnabled = false
        }
        return valid
    }


}
