package com.example.antrikshgyan.presentation.isro_zone.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.antrikshgyan.common.Resource
import com.example.antrikshgyan.domain.model.ISROSpaceCraftModel
import com.example.antrikshgyan.domain.usecase.isro.ISROServiceUseCase
import com.example.antrikshgyan.presentation.isro_zone.state.ISROSpacecraftDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ISROServiceViewModel @Inject constructor(
    private val useCase: ISROServiceUseCase
) : ViewModel() {

    private val TAG = "ISROServiceViewModel"

    private val _spacecraftResponse = mutableStateOf(ISROSpacecraftDataState())
    val spacecraftResponse = _spacecraftResponse

    init {
        getSpacecraft()
    }


    private fun getSpacecraft() {
        viewModelScope.launch {
            useCase.getISROSpacecraft().collect{
                response->
                when(response){
                    is Resource.Success ->{
                        _spacecraftResponse.value = ISROSpacecraftDataState(
                            isLoading = false,
                            spacecraft = response.data!!,
                            error = null.toString()
                            )
                    }

                    is Resource.Error -> {
                        _spacecraftResponse.value = ISROSpacecraftDataState(
                            isLoading = false,
                            error = response.message ?: "Error"
                        )
                        Log.e(TAG, response.message.toString())
                    }
                    is Resource.Loading -> {
                        _spacecraftResponse.value = ISROSpacecraftDataState(
                            isLoading = true
                        )
                        Log.e(TAG, response.message.toString())
                    }
                }
            }
        }
    }
}