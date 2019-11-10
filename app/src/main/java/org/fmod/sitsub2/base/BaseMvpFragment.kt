package org.fmod.sitsub2.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import org.fmod.sitsub2.util.errorLog
import org.fmod.sitsub2.util.toastError
import java.lang.Exception
import java.lang.reflect.ParameterizedType

/**
 * Create by NOSAE on 2019/10/26
 */
abstract class BaseMvpFragment<T: BaseContract.Presenter>: Fragment(), BaseContract.View {

    protected lateinit var mPresenter: T

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun setListeners()

    abstract fun initViews()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createPresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setListeners()
    }

    override fun showErrorToast(msg: String) {
        toastError(msg)
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
}