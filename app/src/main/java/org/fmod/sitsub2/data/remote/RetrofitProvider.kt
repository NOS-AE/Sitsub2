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

    //重用retrofits和clients
    private val retrofits = HashMap<String, Retrofit>()
    private val clients = HashMap<String, OkHttpClient>()

    private fun createClient(token: String): OkHttpClient {
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
    private fun createRetrofit(baseUrl: String, isJson: Boolean, token: String): Retrofit {

        //add client
        val client = clients.getOrDefault(token, createClient(token))

        val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)

        if(isJson){
            builder.addConverterFactory(GsonConverterFactory.create())
        } else {
            builder.addConverterFactory(JaxbConverterFactory.create())
        }
        val res = builder.build()
        retrofits["$baseUrl-$isJson"] = res
        return res
    }

    fun getRetrofit(baseUrl: String, token: String, isJson: Boolean = true): Retrofit {
        val key = "$baseUrl-$isJson"
        return retrofits.getOrDefault(key, createRetrofit(baseUrl, isJson, token))
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