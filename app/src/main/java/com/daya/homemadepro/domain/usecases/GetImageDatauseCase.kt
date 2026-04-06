package com.daya.homemadepro.domain.usecases

import android.content.Context
import com.daya.homemadepro.data.modal.ImagePostResponse
import com.daya.homemadepro.data.repository.ImageResponseReposirotyImpl
import com.daya.homemadepro.domain.repository.ImageResponseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher

class GetImageDatauseCase {

    private val repository : ImageResponseRepository by lazy { ImageResponseReposirotyImpl() }

    operator  fun invoke(q: String, image_type: String, pretty: Boolean) = flow<Result<ImagePostResponse>>{


        val response = repository.getImageResponse(q, image_type, pretty)
        emit(response)

    }.catch {
        emit(Result.failure(it))
    }.flowOn(Dispatchers.IO)


}