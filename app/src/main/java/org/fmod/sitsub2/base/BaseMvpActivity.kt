package org.fmod.sitsub2.base

import android.os.Bundle
import android.os.IInterface
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import org.fmod.sitsub2.util.ThemeUtil
import org.fmod.sitsub2.util.errorLog
import org.fmod.sitsub2.util.log
import org.fmod.sitsub2.util.toastError
import java.lang.Exception
import java.lang.reflect.ParameterizedType

abstract class BaseMvpActivity<T: BaseContract.Presenter>: AppCompatActivity(), BaseContract.View {

    protected lateinit var mPresenter: T
    /**
     * 设置布局
     *
     * @return 布局资源Id
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * 控件监听
     */
    abstract fun setListeners()

    /**
     * 控件初始化
     */
    abstract fun initViews()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeUtil.setTheme(this)
        createPresenter()
        //设置布局
        setContentView(getLayoutId())
        //初始化控件样式
        initViews()
        //设置控件事件监听
        setListeners()
    }

    @Suppress("UNCHECKED_CAST")
    private fun createPresenter() {
        try {
            val type = javaClass.genericSuperclass as ParameterizedType //获取父类type
            val presenterClass = type.actualTypeArguments[0] as Class<*> //获取Presenter真实类型
            val cons = presenterClass.getDeclaredConstructor().apply {  //获取Presenter构造函数
                isAccessible = true
            }
            mPresenter = cons.newInstance() as T
            mPresenter.attach(this)
        } catch (e: Exception) {
            errorLog("构造Presenter失败：${e.message}")
        }

    }

    override fun showErrorToast(msg: String) {
        toastError(msg)
    }

}