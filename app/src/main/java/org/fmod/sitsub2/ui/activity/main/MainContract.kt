package org.fmod.sitsub2.ui.activity.main

import org.fmod.sitsub2.base.BaseContract

interface MainContract {
    interface View: BaseContract.View {

    }
    interface Presenter: BaseContract.Presenter {
        fun getAuthUser()
    }
}