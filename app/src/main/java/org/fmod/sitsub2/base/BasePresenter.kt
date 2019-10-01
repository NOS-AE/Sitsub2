package org.fmod.sitsub2.base

import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.launch
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BasePresenter<T: BaseContract.View>: BaseContract.Presenter {
    protected lateinit var mView: T

    @Suppress("UNCHECKED_CAST")
    override fun attach(view: BaseContract.View) {
        mView = view as T
    }

    protected fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ) = mView.lifecycleScope.launch(context, start, block)

    protected fun launch(
        tryBlock: suspend CoroutineScope.() -> Unit,
        catchBlock: suspend CoroutineScope.(Throwable) -> Unit = {},
        finallyBlock: suspend CoroutineScope.() -> Unit = {}
    ) = mView.lifecycleScope.launch{
        try {
            tryBlock()
        } catch (e: CancellationException) {
            //正常
        } catch (e: Exception) {
            catchBlock(e)
        }finally {
            finallyBlock()
        }
    }

    protected fun getErrorTip(e: Throwable): String {
        return when(e) {
            is UnknownHostException -> "加载失败，请检查网络"
            is SocketTimeoutException -> "加载超时，请检查网络"
            else -> "错误:${e.message}"
        }
    }
}

