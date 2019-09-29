package org.fmod.sitsub2.ui.face.login

import android.view.View
import kotlinx.android.synthetic.main.activity_login.*

import org.fmod.sitsub2.R
import org.fmod.sitsub2.base.BaseMvpActivity
import org.fmod.sitsub2.bean.Suggestion
import org.fmod.sitsub2.data.remote.model.recieve.BasicResponse
import org.fmod.sitsub2.ui.adapter.SuggestionAdapter
import org.fmod.sitsub2.util.*

class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginContract.View {

    private lateinit var username: String
    private lateinit var password: String

    private lateinit var list: ArrayList<Suggestion>

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

        val suggestions = resources.getStringArray(R.array.user_list)

        list = Suggestion.toSuggestionList(suggestions)
        SuggestionAdapter(this, list).run {
            login_id.setAdapter(this)
            login_id.threshold = 0
        }
    }

    override fun setListeners() {
        login_login.setOnClickListener {
            if (!loginCheck())
                return@setOnClickListener
            mPresenter.tryLogin(username, password)
            login_login.visibility = View.INVISIBLE
            login_progressbar.visibility = View.VISIBLE
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
