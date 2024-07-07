package com.example.antrikshgyan.presentation.isro_zone.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.antrikshgyan.common.Resource
import com.example.antrikshgyan.domain.usecase.isro.ISROServiceUseCase
import com.example.antrikshgyan.presentation.isro_zone.state.ISROLaunchDataState
import com.example.antrikshgyan.presentation.isro_zone.state.ISROSpacecraftDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ISROServiceViewModel @Inject constructor(
    private val useCase: ISROServiceUseCase
) : ViewModel() {

    private val TAG = "ISROServiceViewModel"

    private val _spacecraftState = mutableStateOf(ISROSpacecraftDataState())
    val spacecraftResponse = _spacecraftState
    
    private val _launchState = mutableStateOf(ISROLaunchDataState())
    val launchState = _launchState

    init {
        getSpacecraft()
        getLaunches()
    }


    private fun getSpacecraft() {
        viewModelScope.launch {
            useCase.getISROSpacecraft().collect{
                response->
                when(response){
                    is Resource.Success ->{
                        _spacecraftState.value = ISROSpacecraftDataState(
                            isLoading = false,
                            spacecraft = response.data!!,
                            error = null.toString()
                            )
                    }
                    is Resource.Error -> {
                        _spacecraftState.value = ISROSpacecraftDataState(
                            isLoading = false,
                            error = response.message ?: "Error"
                        )
                        Log.e(TAG, response.message.toString())
                    }
                    is Resource.Loading -> {
                        _spacecraftState.value = ISROSpacecraftDataState(
                            isLoading = true
                        )
                        Log.e(TAG, response.message.toString())
                    }
                }
            }
        }
    }
    
    private fun getLaunches(){
        viewModelScope.launch { 
            useCase.getISROLaunches().collect{launches ->
                
                when(launches){
                    is Resource.Success->{
                        _launchState.value = ISROLaunchDataState(
                            isLoading = false,
                            launch = launches.data!!,
                            error = ""
                        )
                    }
                    is Resource.Error -> {
                        _launchState.value = ISROLaunchDataState(
                            isLoading = true,
                            error = launches.message!!
                        )
                    }
                    is Resource.Loading -> {
                        _launchState.value = ISROLaunchDataState(
                            isLoading = true,
                        )
                    }
                }
                
            }
        }
    }
}