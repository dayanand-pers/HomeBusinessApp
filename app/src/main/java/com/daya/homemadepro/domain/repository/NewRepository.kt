package com.daya.homemadepro.domain.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.daya.homemadepro.data.remote.ApiService
import com.daya.homemadepro.data.repository.ImagesPagingSourece
import javax.inject.Inject

class NewRepository @Inject constructor(private val apiService: ApiService) {

    fun getImagesPager(p: String, image_type: String, pretty: Boolean) = Pager(
        config = PagingConfig(
            pageSize = 20
        ),
        pagingSourceFactory = {
            ImagesPagingSourece(apiService).apply {
                setImageParams(p, image_type, pretty)
            }
            ImagesPagingSourece(apiService)
        }
    ).flow
}