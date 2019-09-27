package org.fmod.sitsub2

import androidx.lifecycle.*
import org.fmod.sitsub2.util.log
import java.lang.Exception

/**
 * Map<K,V>
 *     K: 事件类型
 *     V: 事件
 * 将用户主动调用的函数中的observe封装命名为为subscribe
 *
 * 内部管理LiveData
 */
object AppBus {

    //消息通道
    val bus by lazy {
        HashMap<Class<*>, MyLiveData<Any>>()
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified T>subscribe(owner: LifecycleOwner, observer: Observer<in T>) {
        val eventType = T::class.java
        if(!bus.containsKey(eventType)){
            bus[eventType] = MyLiveData()
        }
        (bus[eventType] as MyLiveData<T>?)?.subscribe(owner, observer)
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <reified T>subscribeSticky(owner: LifecycleOwner, observer: Observer<in T>) {
        val eventType = T::class.java
        if(!bus.containsKey(eventType)){
            bus[eventType] = MyLiveData()
        }
        (bus[eventType] as MyLiveData<T>?)?.subscribeSticky(owner, observer)
    }

    inline fun <reified T>post(message: T) {
        val eventType = T::class.java
        if(!bus.containsKey(eventType)){
            bus[eventType] = MyLiveData()
        }
        bus[eventType]?.value = message
    }

    //TODO 清除sticky事件

    class MyLiveData<T>: MutableLiveData<T>() {

        //存放Wrapper
        private val observerMap by lazy {
            HashMap<Observer<in T>, Observer<in T>>()
        }

        /**
         * 不接收订阅前发布的消息
         */
        fun subscribe(owner: LifecycleOwner, observer: Observer<in T>) {
            observe(owner, observer)
            try {
                //处理observe调用时的sticky问题
                hook(observer)
            } catch (e: Exception) {
                log(e.stackTrace.toString())
            }
        }

        /**
         * 接收粘性消息，observe默认粘性
         */
        fun subscribeSticky(owner: LifecycleOwner, observer: Observer<in T>) {
            observe(owner, observer)
        }

        /**
         * 非生命周期同步，hook失效，用Wrapper处理sticky
         */
        fun subscribeForever(observer: Observer<in T>) {
            //用Wrapper封装，处理sticky
            observerMap[observer] = ObserverWrapper(observer)
            observeForever(observerMap[observer] as Observer<in T>)
        }

        override fun removeObserver(observer: Observer<in T>) {
            super.removeObserver(observerMap.remove(observer) ?: observer)
        }

        //将ObserverWrapper的mLastVersion赋值为LiveData中的mVersion
        @Throws(Exception::class)
        private fun hook(observer: Observer<in T>) {
            //get wrapper's version
            val classLiveData = LiveData::class.java
            val fieldObservers = classLiveData.getDeclaredField("mObservers").apply {
                isAccessible = true
            }
            val objectObservers = fieldObservers.get(this)
            val classObservers = objectObservers::class.java
            val methodGet = classObservers.getDeclaredMethod("get", Object::class.java).apply {
                isAccessible = true
            }
            val objectWrapperEntry = methodGet.invoke(objectObservers, observer)//通过key获取entry
            val objectWrapper: Any
            if(objectWrapperEntry is Map.Entry<*,*>){
                objectWrapper = objectWrapperEntry.value as Any
            } else{
                throw NullPointerException("Wrapper can't not be null")
            }
            val classObserverWrapper = objectWrapper::class.java.superclass
            val fieldLastVersion = classObserverWrapper?.getDeclaredField("mLastVersion")?.apply {
                isAccessible = true
            }
            //get liveData's version
            val fieldVersion = classLiveData.getDeclaredField("mVersion").apply {
                isAccessible = true
            }
            val objectVersion = fieldVersion.get(this)
            //set wrapper's version
            fieldLastVersion?.set(objectWrapper, objectVersion)
        }
    }

    //Observer代理，处理observeForever调用时stickyEvent等问题
    private class ObserverWrapper<T>(private var observer: Observer<T>): Observer<T> {

        override fun onChanged(t: T) {
            if(isCallOnObserve())
                return
            observer.onChanged(t)
        }

        /**
         * 检查onChanged是否由调用observeForever触发的，如果是，那么就不属于正常回调
         */
        private fun isCallOnObserve(): Boolean {
            val stackTrace = Thread.currentThread().stackTrace
            stackTrace.forEach {
                if(it.className == "androidx.lifecycle.LiveData" &&
                    it.methodName == "observeForever"){
                    return true
                }
            }
            return false
        }
    }

}