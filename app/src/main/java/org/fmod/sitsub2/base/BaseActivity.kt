package org.fmod.sitsub2.base

import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import org.fmod.sitsub2.util.ThemeUtil

abstract class BaseActivity: AppCompatActivity() {
    /**
     * 设置布局
     *
     * @return 布局资源Id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun setListeners()

    abstract fun initViews()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeUtil.setTheme(this)
        //设置布局
        setContentView(getLayoutId())
        //初始化控件样式
        initViews()
        //设置控件事件监听
        setListeners()
        Log.d("MyApp", "fuck")
    }


}