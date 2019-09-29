package org.fmod.sitsub2.ui.face.main

import org.fmod.sitsub2.R
import org.fmod.sitsub2.base.BaseMvpActivity

class MainActivity : BaseMvpActivity<MainPresenter>(), MainContract.View {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initViews() {
        //injectPresenter(MainPresenter())
    }

    override fun setListeners() {

    }

}
