package com.example.antrikshgyan.presentation.mars.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.antrikshgyan.common.Resource
import com.example.antrikshgyan.domain.usecase.nasa.MarsRoverImageUseCase
import com.example.antrikshgyan.presentation.mars.state.MarsRoverImageDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarsRoverImageViewModel @Inject constructor(
    private val marsRoverImageUseCase: MarsRoverImageUseCase
) : ViewModel(){

    private val _marsRoverImageViewModel = mutableStateOf(MarsRoverImageDataState())
    val marsRoverImageViewModel = _marsRoverImageViewModel

    fun getMarRoverImage(
        sol : Int,
        page : Int
    ){
        viewModelScope.launch {
            marsRoverImageUseCase.getMarsRoversImages(sol, page).collect{response->
                when(response){
                    is Resource.Loading->{
                        _marsRoverImageViewModel.value = MarsRoverImageDataState(
                            isLoading = true
                        )
                    }
                    is Resource.Error -> {
                        _marsRoverImageViewModel.value = MarsRoverImageDataState(
                            error = response.message ?: "Error"
                        )
                    }
                    is Resource.Success -> {
                        _marsRoverImageViewModel.value = MarsRoverImageDataState(
                            isLoading = false,
                            marsRoverImage = response.data!!
                        )
                    }
                }
            }
        }
    }
}