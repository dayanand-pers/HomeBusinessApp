package com.daya.homemadepro.domain.repository

import com.daya.homemadepro.data.modal.ImagePostResponse

interface ImageResponseRepository {

    suspend fun getImageResponse(q : String, image_type : String, pretty : Boolean) : Result<ImagePostResponse>
}