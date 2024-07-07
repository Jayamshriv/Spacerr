package com.example.antrikshgyan.presentation.apod.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.antrikshgyan.common.Resource
import com.example.antrikshgyan.domain.model.APODModel
import com.example.antrikshgyan.domain.usecase.nasa.APODUseCase
import com.example.antrikshgyan.presentation.apod.APODDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class APODViewModel @Inject constructor(
    private val useCase: APODUseCase
) : ViewModel() {

    private val TAG = "APODViewModel"

    private val _state = mutableStateOf(APODDataState())
    val state by _state

    init {
        getAPOD()
    }

    private fun getAPOD() {
        viewModelScope.launch {
            useCase.getAPOD().collect { response ->
                when (response) {
                    is Resource.Success ->
                        {
                        val result = response.data!!
                        _state.value = APODDataState(apod = result ?: APODModel())
                    }

                    is Resource.Loading ->{
                        _state.value = APODDataState(isLoading = true)
                        Log.e(TAG, response.message.toString())
                    }

                    is Resource.Error -> {
                        _state.value = APODDataState(error = response.message ?: "Error")
                        Log.e(TAG, response.message.toString())
                    }
                }

            }

        }
    }

}