package org.fmod.sitsub2.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jaxb.JaxbConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import org.fmod.sitsub2.util.log
import org.fmod.sitsub2.util.remoteLog
import java.util.concurrent.TimeUnit

class RetrofitProvider private constructor(){

    companion object {
        val instance by lazy {
            RetrofitProvider()
        }
    }

    private val retrofits = HashMap<String, Retrofit>() //重用retrofits和clients
    private var token: String? = "" //被拦截器加到Header中

    private fun createClient(): OkHttpClient {
        val logInterceptor = HttpLoggingInterceptor(object :HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                remoteLog(message)
            }
        }).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .connectTimeout(HTTP_TIME_OUT, TimeUnit.MILLISECONDS)
            .addInterceptor(logInterceptor)
            .addInterceptor(BasicInterceptor())
            /*.addNetworkInterceptor {

            }*/
            .build()
    }

    //通过baseUrl和返回数据是否JSON，创建多个retrofit实例
    private fun createRetrofit(baseUrl: String, isJson: Boolean, token: String?): Retrofit {
        this.token = token
        val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createClient())

        if(isJson){
            builder.addConverterFactory(GsonConverterFactory.create()) //JSON
        } else {
            builder.addConverterFactory(JaxbConverterFactory.create()) //XML
        }
        val res = builder.build()
        retrofits["$baseUrl-$isJson"] = res
        return res
    }

    fun getRetrofit(baseUrl: String, token: String?, isJson: Boolean = true): Retrofit {
        val key = "$baseUrl-$isJson"
        return retrofits.getOrDefault(key, createRetrofit(baseUrl, isJson, token))
    }

    private inner class BasicInterceptor: Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val requestBuilder = chain.request().newBuilder()
            //add unique login id in url to differentiate caches

            //add access token
            val safeToken = token
            if(!safeToken.isNullOrBlank()){
                val submitToken = if (safeToken.startsWith("Basic")) safeToken else "token $safeToken"
                requestBuilder
                    .addHeader("Authorization",submitToken)
                    .build()
            }
            //第二次请求，强制使用网络请求

            return chain.proceed(requestBuilder.build())
        }
    }
}
