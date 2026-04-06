package com.daya.homemadepro.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.daya.homemadepro.data.modal.ImagePostResponse
import com.daya.homemadepro.domain.repository.NewRepository
import com.daya.homemadepro.domain.usecases.GetImageDatauseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class PostImageViewModel @Inject constructor( private val newRepository: NewRepository) : ViewModel() {





    private val usecase : GetImageDatauseCase by lazy { GetImageDatauseCase() }

    private val _uiState = MutableStateFlow(UiImageStates())

    val uiState = _uiState.asStateFlow()

    private val _query = MutableStateFlow("")

    fun updateInput(input : String){
        _query.update{ input }
    }


    fun getImages( imageName : String) = newRepository.getImagesPager(imageName, "photo", true).cachedIn(viewModelScope)


    init {

        viewModelScope.launch {

            _query.filter { it.isNotEmpty() }
                .collectLatest { request ->
                    usecase(request, "photo", true).onStart {
                        _uiState.update {  UiImageStates(isLoading = true)}
                    }
                        .onEach { result ->
                            if(result.isSuccess){
                               _uiState.update { UiImageStates(successdata = result.getOrThrow()) }
                            }else{
                                _uiState.update { UiImageStates(isFailed = result.exceptionOrNull()?.message.toString()) }
                            }
                        }
                        .catch { error ->
                            _uiState.update {  UiImageStates(isFailed = error.message.toString()) }
                        }.launchIn(viewModelScope)
                }

        }


    }
}

data class UiImageStates(
    val isLoading : Boolean = false,
    val isFailed : String = "",
    val successdata : ImagePostResponse? = null
)