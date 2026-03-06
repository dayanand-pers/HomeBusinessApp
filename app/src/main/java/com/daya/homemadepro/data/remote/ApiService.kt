package com.daya.homemadepro.data.remote

import com.daya.homemadepro.data.modal.Data
import com.daya.homemadepro.data.modal.LoginRequestEncrypted
import com.daya.homemadepro.data.modal.LoginRequestX
import com.daya.homemadepro.data.modal.LoginResponse
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    @POST("user/login")
    @Headers("Content-Type: application/json")
    suspend fun loginToapplication(@Body LoginRequestEncrypted : LoginRequestEncrypted): LoginRequestX



}