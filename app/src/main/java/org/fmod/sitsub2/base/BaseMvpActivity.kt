package org.fmod.sitsub2.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import org.fmod.sitsub2.util.ThemeUtil
import org.fmod.sitsub2.util.errorLog
import org.fmod.sitsub2.util.toastError
import java.lang.Exception
import java.lang.reflect.ParameterizedType

abstract class BaseMvpActivity<T: BaseContract.Presenter>: AppCompatActivity(), BaseContract.View {

    protected lateinit var mPresenter: T

    protected lateinit var mToolbar: Toolbar
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

    /**
     * 继承控件初始化
     */
    abstract fun getViews()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeUtil.setTheme(this)
        createPresenter()
        setContentView(getLayoutId())
        getViews()
        initToolbar()
        initViews()
        setListeners()
    }

    protected open fun initToolbar() {
        if(!this::mToolbar.isInitialized) return
        setSupportActionBar(mToolbar)
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
            errorLog(e, "构造Presenter失败")
        }
    }

    override fun showErrorToast(msg: String) {
        toastError(msg)
    }

}