package com.daya.homemadepro.data.remote

import com.daya.homemadepro.data.modal.Data
import com.daya.homemadepro.data.modal.ImagePostResponse
import com.daya.homemadepro.data.modal.LoginRequestEncrypted
import com.daya.homemadepro.data.modal.LoginRequestX
import com.daya.homemadepro.data.modal.LoginResponse
import dagger.Module
import dagger.hilt.InstallIn
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {

    @POST("user/login")
    @Headers("Content-Type: application/json")
    suspend fun loginToapplication(@Body LoginRequestEncrypted : LoginRequestEncrypted): LoginRequestX


    //https://pixabay.com/api/?key=55263908-6c7093e1e42df37c56d98313b&q=yellow+flowers&image_type=photo&pretty=true

    @GET("api/")
    suspend fun getImageData(
        @Query("key") Key : String = "55263908-6c7093e1e42df37c56d98313b",
        @Query("q") q : String,
        @Query("image_type") image_type : String,
        @Query("pretty") pretty : Boolean,
        @Query("page") page : Int
    ) : ImagePostResponse




}