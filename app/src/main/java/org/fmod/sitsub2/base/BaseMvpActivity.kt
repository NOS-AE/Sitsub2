package org.fmod.sitsub2.base

import android.os.Bundle
import org.fmod.sitsub2.util.errorLog
import org.fmod.sitsub2.util.toastError
import java.lang.Exception
import java.lang.reflect.ParameterizedType

abstract class BaseMvpActivity<T: BaseContract.Presenter>: BaseActivity(), BaseContract.View {

    protected lateinit var mPresenter: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createPresenter()
        onPresenterCreated()
    }

    open fun onPresenterCreated() {

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