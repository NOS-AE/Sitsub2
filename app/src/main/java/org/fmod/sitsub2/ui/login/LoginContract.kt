package org.fmod.sitsub2.ui.login

import org.fmod.sitsub2.base.BasePresenter
import org.fmod.sitsub2.base.BaseView

interface LoginContract {
    interface Presenter: BasePresenter {

    }
    interface View: BaseView<Presenter> {

    }
}