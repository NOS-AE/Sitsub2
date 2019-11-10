package org.fmod.sitsub2.ui.activity.main

import org.fmod.sitsub2.AppData
import org.fmod.sitsub2.base.BasePresenter
import org.fmod.sitsub2.data.DataManager

class MainPresenter: BasePresenter<MainContract.View>(), MainContract.Presenter {
    override fun getAuthUser() {
        launch {
            AppData.authUser = DataManager.authUserDao.findSelected()
        }
    }
}