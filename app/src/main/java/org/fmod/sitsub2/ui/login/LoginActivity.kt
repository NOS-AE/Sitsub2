package org.fmod.sitsub2.ui.login

import kotlinx.android.synthetic.main.activity_login.*
import org.fmod.sitsub2.R
import org.fmod.sitsub2.base.BaseActivity
import org.fmod.sitsub2.util.transparentFullScreen

class LoginActivity : BaseActivity(), LoginContract.View {

    private lateinit var presenter: LoginContract.Presenter

    override fun getLayoutId() = R.layout.activity_login

    override fun initViews() {
        transparentFullScreen()
    }

    override fun setListeners() {
        login_login.setOnClickListener {

        }
    }

    override fun injectPresenter(presenter: LoginContract.Presenter) {
        this.presenter = presenter
    }
}
