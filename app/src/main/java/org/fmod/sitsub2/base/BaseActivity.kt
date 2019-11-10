package org.fmod.sitsub2.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*
import org.fmod.sitsub2.R
import org.fmod.sitsub2.data.DataManager
import org.fmod.sitsub2.util.log
import org.fmod.sitsub2.util.statusBarHeight

/**
 * Create by NOSAE on 2019/11/3
 */
abstract class BaseActivity: AppCompatActivity() {

    protected var mToolbar: Toolbar? = null

    @LayoutRes
    abstract fun getLayoutId(): Int

    open fun setListeners() {
        mToolbar?.setNavigationOnClickListener {
            finish()
        }
    }

    open fun initViews() {
        mToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(mToolbar)
        mToolbar?.setPadding(0, statusBarHeight, 0, 0)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(if (DataManager.isDarkTheme) R.style.ThemeDark else R.style.ThemeLight)
        setContentView(getLayoutId())
        initViews()
        setListeners()
    }

}