package org.fmod.sitsub2.ui.face.main

import android.graphics.Color
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_nav_header.view.*
import org.fmod.sitsub2.R
import org.fmod.sitsub2.base.BaseDrawerActivity
import org.fmod.sitsub2.util.statusBarHeight
import org.fmod.sitsub2.util.transparentFullScreen

class MainActivity : BaseDrawerActivity<MainPresenter>(), MainContract.View {

    private var accountMode = false

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initViews() {
        super.initViews()
        transparentFullScreen()
        toolbar.run {
            setPadding(0, statusBarHeight, 0, 0)
        }
        refresh_layout.setColorSchemeResources(
            R.color.refresh_color1,
            R.color.refresh_color2,
            R.color.refresh_color3,
            R.color.refresh_color4
        )
    }

    override fun setListeners() {
        mHeader.toggle_account_bn.setOnClickListener {
            invalidateDrawerMenu()
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        invalidateDrawerMenu()
        return super.onPrepareOptionsMenu(menu)
    }

    private fun invalidateDrawerMenu() {
        val menu = nav_start.menu

        menu.run {
            setGroupVisible(R.id.user_operation, accountMode)
            setGroupVisible(R.id.user_manage, accountMode)

            setGroupVisible(R.id.me, !accountMode)
            setGroupVisible(R.id.repository, !accountMode)
            setGroupVisible(R.id.discovery, !accountMode)
            setGroupVisible(R.id.settings, !accountMode)
        }
        accountMode = !accountMode
    }

    /**
     * 获取drawer和navView，给父类管理
     */
    override fun getViews() {
        mDrawer = drawer
        mNavStart = nav_start
        mToolbar = toolbar
    }

    /**
     * 侧边栏选项
     */
    override fun onNavItemSelected(item: MenuItem) {

    }

}
