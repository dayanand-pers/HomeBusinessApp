package com.daya.homemadepro.data.remote

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


object RetrofitInstance {

    fun getInstance() : Retrofit {

        val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {}
            override fun getAcceptedIssuers(): Array<X509Certificate> = arrayOf()
        })

// Configure OkHttpClient to use the "trust all" TrustManager
        val sslContext = SSLContext.getInstance("SSL")
        sslContext.init(null, trustAllCerts, SecureRandom())

        val loginintercepter = HttpLoggingInterceptor().apply {
            HttpLoggingInterceptor.Level.BODY
        }

        val ohhttpClient = OkHttpClient.Builder()
            .sslSocketFactory(sslContext.socketFactory, trustAllCerts[0] as X509TrustManager)
            .addInterceptor(loginintercepter)
            .build()

        return Retrofit.Builder()
            .client(ohhttpClient)
            .baseUrl("https://callapi.velapro.com/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }


    fun getLoginApiService() : ApiService{

        Log.e("Retrofit", "API service called")
        return getInstance().create(ApiService::class.java)
    }

}