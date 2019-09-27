package org.fmod.sitsub2.ui.login

import android.annotation.SuppressLint
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_login.*
import org.fmod.sitsub2.AppBus

import org.fmod.sitsub2.AppBus2
import org.fmod.sitsub2.R
import org.fmod.sitsub2.base.BaseActivity
import org.fmod.sitsub2.data.remote.model.recieve.BasicResponse
import org.fmod.sitsub2.model.BusBean
import org.fmod.sitsub2.ui.main.MainActivity
import org.fmod.sitsub2.util.*

class LoginActivity : BaseActivity<LoginContract.Presenter>(), LoginContract.View {

    private lateinit var username: String
    private lateinit var password: String

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
        //TODO 解决耦合
        injectPresenter(LoginPresenter(this))

        transparentFullScreen()

        AppBus.subscribe<String>(this, Observer {
            log("Login observe $it")
        })
        AppBus.subscribe<BusBean>(this, Observer {
            log("Login observe BusBean ${it.message}")
        })

        val suggestions = resources.getStringArray(R.array.user_list)
        ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, suggestions).also {
            login_id.setAdapter(it)
        }

    }

    override fun setListeners() {
        login_login.setOnClickListener {
            /*if(!loginCheck())
                return@setOnClickListener
            login_login.visibility = View.INVISIBLE
            login_progressbar.visibility = View.VISIBLE
            mPresenter.tryLogin(username, password)*/

            AppBus.post("fuck")
            startActivity<MainActivity>()
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
