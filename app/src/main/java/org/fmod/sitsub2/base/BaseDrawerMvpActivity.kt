package org.fmod.sitsub2.base

import android.os.Handler
import android.view.MenuItem
import android.view.View
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import org.fmod.sitsub2.R
import org.fmod.sitsub2.util.log

/**
 * 处理左边（Start）的抽屉
 * TODO 右抽屉
 */
abstract class BaseDrawerMvpActivity<T: BaseContract.Presenter>: BaseMvpActivity<T>() {

    protected var mNavStart: NavigationView? = null
    protected var mDrawer: DrawerLayout? = null
    protected var mHeader: View? = null

    abstract fun onNavItemClick(item: MenuItem)

    override fun initViews() {
        super.initViews()
        mDrawer = findViewById(R.id.drawer)
        mNavStart = findViewById(R.id.nav_start)
        mHeader = mNavStart?.getHeaderView(0)
        initNavStart()
    }

    override fun setListeners() {
        super.setListeners()
        mToolbar?.setNavigationOnClickListener {
            openDrawer()
        }
    }

    private fun initNavStart() = mNavStart?.let {
        mHeader = it.getHeaderView(0)
        it.setNavigationItemSelectedListener {
            closeDrawer()
            Handler().postDelayed({
                onNavItemClick(it)
            }, 250L)
        }
    }

    private fun openDrawer() {
        mDrawer?.openDrawer(GravityCompat.START)
    }

    private fun closeDrawer() {
        mDrawer?.closeDrawers()
    }

    override fun onBackPressed() {
        mDrawer?.let {
            if (it.isDrawerOpen(GravityCompat.START) || it.isDrawerOpen(GravityCompat.END))
                closeDrawer()
            else
                super.onBackPressed()
        } ?: super.onBackPressed()
    }

}