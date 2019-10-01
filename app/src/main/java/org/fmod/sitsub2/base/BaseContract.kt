package org.fmod.sitsub2.base

import androidx.lifecycle.LifecycleOwner

interface BaseContract {
    interface Presenter {
        fun attach(view: View)
    }

    interface View: LifecycleOwner {
        fun showErrorToast(msg: String)
    }
}