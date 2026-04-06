package com.daya.homemadepro.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.daya.homemadepro.data.modal.Hit
import com.daya.homemadepro.data.modal.ImagePostResponse
import com.daya.homemadepro.data.remote.ApiService

class ImagesPagingSourece(private val apiService: ApiService) : PagingSource<Int, Hit>() {

    var q : String = "";
    var image_type : String = "";
    var pretty : Boolean = false;


    /*To find out form which source of page your date should be load
    in the case of refresh otherwise it will load from first page*/
    override fun getRefreshKey(state: PagingState<Int, Hit>): Int? {

        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    fun setImageParams(q: String, image_type: String, pretty: Boolean){

        this.q = q
        this.image_type = image_type
        this.pretty = pretty

    }

    /*Load the data as per pages provided in the LoadParams Its a param is sealed class.
    * Its suspend coroutine function works async to call remote or local data.
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hit> {
        return try {

            val page = params.key ?: 1
            val response = apiService.getImageData( "55263908-6c7093e1e42df37c56d98313b", q, image_type, pretty, page)

            LoadResult.Page(
                data = response.hits,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey =if (response.hits.isEmpty()) null else page + 1
            )

        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}