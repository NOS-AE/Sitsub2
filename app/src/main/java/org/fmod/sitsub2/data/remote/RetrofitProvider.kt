package org.fmod.sitsub2.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.jaxb.JaxbConverterFactory

object RetrofitProvider {

    private val retrofits = HashMap<String, Retrofit>()
    private lateinit var token: String

    private fun createRetrofit(baseUrl: String, isJson: Boolean) {
        val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
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

    /**
     * 拦截器
     */

    /**
     * 网络请求拦截器
     */
}