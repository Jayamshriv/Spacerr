package com.example.antrikshgyan.presentation.mars.viewmodel

import android.util.Log
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
     val TAG = "MarsRoverImageViewModel"
    private val _marsRoverImageViewModel = mutableStateOf(MarsRoverImageDataState())
    val marsRoverImageViewModel = _marsRoverImageViewModel

    fun getMarRoverImage(
        sol : Int,
        page : Int
    ){
        val startTime = System.currentTimeMillis()
        viewModelScope.launch {
            marsRoverImageUseCase.getMarsRoversImages(sol, page).collect{response->
                when(response){
                    is Resource.Loading->{
                        val endTime = System.currentTimeMillis()
                        Log.d("Network Time", "API call took: $endTime to $startTime} ${endTime - startTime} ms")
                        _marsRoverImageViewModel.value = MarsRoverImageDataState(
                            isLoading = true
                        )
                        Log.e(TAG, _marsRoverImageViewModel.toString())
                    }
                    is Resource.Error -> {
                        val endTime = System.currentTimeMillis()
                        Log.d("Network Time", "API call took: $endTime to $startTime} ${endTime - startTime} ms")
                        _marsRoverImageViewModel.value = MarsRoverImageDataState(
                            error = response.message ?: "Error"
                        )
                        Log.e(TAG, _marsRoverImageViewModel.toString())
                    }
                    is Resource.Success -> {
                        val endTime = System.currentTimeMillis()
                        Log.d("Network Time", "API call took: $endTime to $startTime} ${endTime - startTime} ms")
                        _marsRoverImageViewModel.value = MarsRoverImageDataState(
                            isLoading = false,
                            marsRoverImage = response.data!!
                        )
                        Log.e(TAG, _marsRoverImageViewModel.toString())
                    }
                }
            }
        }
    }
}