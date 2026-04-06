package com.daya.homemadepro.data.repository

import com.daya.homemadepro.data.modal.ImagePostResponse
import com.daya.homemadepro.data.remote.RetrofitInstance
import com.daya.homemadepro.domain.repository.ImageResponseRepository

class ImageResponseReposirotyImpl : ImageResponseRepository {


    val apiService by lazy { RetrofitInstance.getLoginApiService() }

    override suspend fun getImageResponse(
        q: String,
        image_type: String,
        pretty: Boolean
    ): Result<ImagePostResponse> {


        return try {

            val response = apiService.getImageData("", q = q, image_type = image_type, pretty = pretty, 1)
            Result.success(response)

        }catch (e:Exception){
            Result.failure<ImagePostResponse>(e)
        }

    }


}