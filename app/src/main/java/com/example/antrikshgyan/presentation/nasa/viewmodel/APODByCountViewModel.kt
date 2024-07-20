package com.example.antrikshgyan.presentation.nasa.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.antrikshgyan.common.Resource
import com.example.antrikshgyan.domain.usecase.nasa.APODByCountUseCase
import com.example.antrikshgyan.presentation.nasa.state.APODByCountDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class APODByCountViewModel @Inject constructor(
    private val useCase: APODByCountUseCase
) : ViewModel() {
    val TAG = "APODByCountViewModel"
    private val _apodList = MutableStateFlow(APODByCountDataState())
    val apodList: StateFlow<APODByCountDataState> = _apodList.asStateFlow()

    fun getAPODByCount(count: Int) {
        viewModelScope.launch {
            useCase.getAPODByCount(count).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        Log.e(TAG,"Success $response")
                        _apodList.value = APODByCountDataState(
                            isLoading = false,
                            apodList = response.data
                        )
                    }

                    is Resource.Loading -> {
                        Log.e(TAG,"loading ${response.data}")
                        _apodList.value = APODByCountDataState(
                            isLoading = true
                        )
                    }

                    is Resource.Error -> {
                        Log.e(TAG,"error  ${response.message}")
                        _apodList.value = APODByCountDataState(
                            isLoading = false,
                            error = response.message ?: "Unknown Error!!!"
                        )
                    }
                }
            }
        }
    }

}