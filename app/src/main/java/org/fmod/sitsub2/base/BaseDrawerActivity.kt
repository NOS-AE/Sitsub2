package org.fmod.sitsub2.base

import android.os.Handler
import android.view.MenuItem
import android.view.View
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import org.fmod.sitsub2.util.log


/**
 * 处理左边（Start）的抽屉
 */
abstract class BaseDrawerActivity<T: BaseContract.Presenter>: BaseMvpActivity<T>() {

    protected lateinit var mNavStart: NavigationView
    protected lateinit var mDrawer: DrawerLayout
    protected lateinit var mHeader: View

    override fun initViews() {
        if(this::mDrawer.isInitialized) {
            initStartDrawer()
            initEndDrawer()
        }
    }

    override fun initToolbar() {
        super.initToolbar()
        mToolbar.setNavigationOnClickListener {
            log("navigation click")
            openDrawer(true)
        }
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    private fun initStartDrawer() {
        if(!this::mDrawer.isInitialized) return
        mHeader = mNavStart.getHeaderView(0)
        mNavStart.setNavigationItemSelectedListener {
            return@setNavigationItemSelectedListener onNavigationItemSelected(it, true)
        }
    }

    private fun openDrawer(isStart: Boolean) {
        if(this::mDrawer.isInitialized) {
            mDrawer.openDrawer(if (isStart) GravityCompat.START else GravityCompat.END)
        }
    }

    private fun initEndDrawer() {

    }

    private fun onNavigationItemSelected(item: MenuItem, isStartDrawer: Boolean): Boolean {
        closeDrawer()
        Handler().postDelayed({
            onNavItemSelected(item)
        },250L)
        return true
    }

    protected abstract fun onNavItemSelected(item: MenuItem)

    protected fun closeDrawer() {
        if(this::mDrawer.isInitialized) {
            mDrawer.closeDrawers()
        }
    }

    override fun onBackPressed() {
        if(this::mDrawer.isInitialized &&
            (mDrawer.isDrawerOpen(GravityCompat.START) || mDrawer.isDrawerOpen(GravityCompat.END)))
            closeDrawer()
        else
            super.onBackPressed()
    }

}