package org.fmod.sitsub2.ui.activity.main

import android.os.Handler
import android.util.TypedValue
import android.view.Menu
import android.view.MenuItem
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.main_app_bar.*
import kotlinx.android.synthetic.main.main_content.*
import kotlinx.android.synthetic.main.main_nav_start.*
import kotlinx.android.synthetic.main.main_nav_header.view.*
import org.fmod.sitsub2.AppData
import org.fmod.sitsub2.R
import org.fmod.sitsub2.base.BaseDrawerMvpActivity
import org.fmod.sitsub2.data.DataManager
import org.fmod.sitsub2.ui.activity.login.LoginActivity
import org.fmod.sitsub2.ui.adapter.MainPagerAdapter
import org.fmod.sitsub2.util.*
import org.fmod.sitsub2.view.FilterSheetDialogFragment
import kotlin.properties.Delegates

class MainActivity : BaseDrawerMvpActivity<MainPresenter>(), MainContract.View {

    private var accountMode = false
    private var navWidth by Delegates.observable(0) { _, _, _ -> setBottom() }
    private var width1 by Delegates.observable(0) { _, _, _ -> setBottom() }
    private var width2 by Delegates.observable(0) { _, _, _ -> setBottom() }
    private var width3 by Delegates.observable(0) { _, _, _ -> setBottom() }
    private var appBarHeight by Delegates.observable(0) { _, _, _ -> setFrame() }

    private lateinit var filterSheet: FilterSheetDialogFragment

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initViews() {
        super.initViews()
        transparentFullScreen()
        val colorId = TypedValue()
        theme.resolveAttribute(R.attr.colorAccent, colorId, true)
        val user = AppData.loggedUser
        if(user != null) {
            mHeader?.run {
                avatar.loadUrl(user.avatarUrl)
                name.text = if (user.name.isBlank()) user.login else user.name
                val joinTime = "加入时间 ${user.createdAt}"
                bio.text = if (user.bio.isBlank()) joinTime else user.bio
            }

        }
        main_pager.adapter = MainPagerAdapter(supportFragmentManager)
        tab.setViewPager(main_pager)
        theme_mode.text = if (DataManager.isDarkTheme) getString(R.string.dark) else getString(R.string.light)
        filterSheet = FilterSheetDialogFragment()

        //适配距离参数
        nav_start.post { navWidth = nav_start.width }
        theme_mode.post { width1 = theme_mode.width }
        settings.post { width2 = settings.width }
        exit.post { width3 = exit.width }
        appbar.post { appBarHeight = appbar.height }

    }

    private fun setBottom() {
        if (navWidth > 0 && width1 > 0 &&  width2 > 0 && width3 > 0) {
            val horizontal = (navWidth - width1 - width2 - width3) / 6
            val vertical = dp2px(15f)
            theme_mode.setPadding(horizontal, vertical, horizontal, vertical)
            settings.setPadding(horizontal, vertical, horizontal, vertical)
            exit.setPadding(horizontal, vertical, horizontal, vertical)
        }
    }

    private fun setFrame() {
        (main_pager.layoutParams as CoordinatorLayout.LayoutParams).setMargins(0,appBarHeight,0,0)
    }

    override fun setListeners() {
        super.setListeners()
        mHeader?.toggle_account_bn?.setOnClickListener {
            invalidateDrawerMenu()
        }
        main_pager.addOnPageChangeListener(object: ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                if (position == 1) {
                    main_filter.hide()
                } else if (position == 0) {
                    main_filter.show()
                }
            }
        })

        main_filter.setOnClickListener {
            filterSheet.show(supportFragmentManager, "dialog")
        }

        theme_mode.setOnClickListener {
            switchTheme()
        }

        settings.setOnClickListener {
            mDrawer?.closeDrawers()
            Handler().postDelayed({

            }, 250L)
        }

        exit.setOnClickListener {
            finish()
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        invalidateDrawerMenu()
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    /**
     * 更换主题
     * TODO 截屏+fade out+遍历元素更换颜色
     */
    private fun switchTheme() {
        val cur = !DataManager.isDarkTheme
        DataManager.isDarkTheme = cur
        if (cur) toNight() else toDay()
        recreate()
    }

    private fun toNight() {
        setTheme(R.style.ThemeDark)
    }

    private fun toDay() {
        setTheme(R.style.ThemeLight)
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

    override fun onNavItemClick(item: MenuItem) {
        when(item.itemId) {
            R.id.nav_logout -> {
                startActivity<LoginActivity>()
                finish()
            }
            R.id.nav_add_user -> {

            }
            R.id.nav_notifications -> {

            }
            R.id.nav_issues -> {

            }
            R.id.nav_starred -> {

            }
            R.id.nav_bookmarks -> {

            }
            R.id.nav_trace -> {

            }
            R.id.nav_trending -> {

            }
            R.id.nav_collections -> {

            }
            R.id.nav_topics -> {

            }
            R.id.nav_global_news -> {

            }
            R.id.nav_settings -> {

            }
            R.id.nav_about -> {

            }
        }
    }


}
