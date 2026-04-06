package com.daya.homemadepro.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.daya.homemadepro.domain.repository.NewRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor( private val newRepository: NewRepository) : ViewModel() {


    fun getImages( imageName : String) = newRepository.getImagesPager(imageName, "photo", true).cachedIn(viewModelScope)

}