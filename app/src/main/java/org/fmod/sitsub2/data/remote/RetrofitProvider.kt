package org.fmod.sitsub2.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jaxb.JaxbConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import org.fmod.sitsub2.util.remoteLog

object RetrofitProvider {

    private val retrofits = HashMap<String, Retrofit>()
    private lateinit var token: String

    private fun createClient(): OkHttpClient {
        val logInterceptor = HttpLoggingInterceptor(object :HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                remoteLog(message)
            }
        }).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .addInterceptor(BasicInterceptor(token))
            /*.addNetworkInterceptor {

            }*/
            .build()


    }

    //通过baseUrl和返回数据是否JSON，创建多个retrofit实例
    private fun createRetrofit(baseUrl: String, isJson: Boolean) {

        val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createClient())

        if(isJson){
            builder.addConverterFactory(GsonConverterFactory.create())
        } else {
            builder.addConverterFactory(JaxbConverterFactory.create())
        }
        retrofits["$baseUrl-$isJson"] = builder.build()
    }

    fun getRetrofit(baseUrl: String, token: String, isJson: Boolean = true): Retrofit {
        this.token = token
        val key = "$baseUrl-$isJson"
        if(!retrofits.containsKey(key)) {
            createRetrofit(baseUrl, isJson)
        }
        return retrofits[key] as Retrofit
    }
}


class BasicInterceptor(private val token: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        //add unique login id in url to differentiate caches

        //add access token（basic）
        if(!token.isBlank()){
            requestBuilder
                .addHeader("Authorization", if(token.startsWith("Basic")) token else "token $token")
                .build()
        }
        //第二次请求，强制使用网络请求

        return chain.proceed(requestBuilder.build())
    }
}