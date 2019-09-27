package org.fmod.sitsub2.ui.main

import androidx.lifecycle.Observer
import org.fmod.sitsub2.AppBus
import org.fmod.sitsub2.R
import org.fmod.sitsub2.base.BaseActivity
import org.fmod.sitsub2.model.BusBean
import org.fmod.sitsub2.util.log

class MainActivity : BaseActivity<MainContract.Presenter>(), MainContract.View {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initViews() {
        injectPresenter(MainPresenter())
    }

    override fun setListeners() {


        AppBus.subscribeSticky<String>(this, Observer {
            log("Main sticky observe $it")
        })
        AppBus.subscribe<String>(this, Observer {
            log("Main observe $it")
        })
        AppBus.post(BusBean("bean fuck"))
        /*AppBus.with<String>()
            .subscribe(this, Observer {
                log("Main observe $it")
            })*/
    }

}
