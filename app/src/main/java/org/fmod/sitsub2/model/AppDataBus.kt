package org.fmod.sitsub2.model

import androidx.lifecycle.MutableLiveData

object AppDataBus {

    private val bus by lazy {
        HashMap<String, MyLiveData<Any>>()
    }

    @Suppress("UNCHECKED_CAST")
    fun <T>with(target: String, type: Class<T>): MyLiveData<T> {
        return bus.getOrDefault(target, MyLiveData()) as MyLiveData<T>
    }

    inline fun <reified T>with(target: String): MyLiveData<T> = with(target, T::class.java)

    class MyLiveData<T>: MutableLiveData<T>() {

        /*override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
            super.observe(owner, observer)
            try {
                hook(observer)
            } catch (e: Exception) {
                log(e.message)
            }
        }

        private fun hook(observer: Observer<in T>) {
            val classLiveData = LiveData::class.java
            val fieldObservers = classLiveData.getDeclaredField("mObservers")

        }*/
    }

}