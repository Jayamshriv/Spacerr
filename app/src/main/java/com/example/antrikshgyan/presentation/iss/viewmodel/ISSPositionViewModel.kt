package com.example.antrikshgyan.presentation.iss.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.antrikshgyan.common.Resource
import com.example.antrikshgyan.domain.usecase.iss.ISSPositionDetailUseCase
import com.example.antrikshgyan.domain.usecase.iss.ISSPositionUseCase
import com.example.antrikshgyan.domain.usecase.iss.ISSTLESUseCase
import com.example.antrikshgyan.presentation.iss.state.ISSPositionDataState
import com.example.antrikshgyan.presentation.iss.state.ISSPositionDetailDataState
import com.example.antrikshgyan.presentation.iss.state.ISSTLESDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ISSPositionViewModel @Inject constructor(
    private val issPositionUseCase: ISSPositionUseCase,
    private val issPositionDetailUseCase: ISSPositionDetailUseCase,
    private val issTLESUseCase: ISSTLESUseCase
) : ViewModel() {

    private val TAG = "ISSPositionViewModel"

    //    private val _issPosition = mutableStateOf(ISSPositionDataState())
    var issPos by mutableStateOf(ISSPositionDataState())
        private set

    private val _issPositionDetail = MutableStateFlow(ISSPositionDetailDataState())
    val issPositionDetail = _issPositionDetail.asStateFlow()

    private val _issTLES = MutableStateFlow(ISSTLESDataState())
    val issTLES = _issTLES.asStateFlow()

    init {
        getISSTLES()
    }

    fun getISSPosition(units: String) {
        viewModelScope.launch {
            issPositionUseCase.getISSPosition(units).collect { response ->
                Log.e("TAG3", "Response : $response")
                when (response) {
                    is Resource.Success -> {
                        Log.e("TAG4", ISSPositionDataState().toString())
                        response.data?.let { re ->
                            issPos = issPos.copy(
                                issPosition = re
                            )
                        }
                        Log.e("TAG5", ISSPositionDataState().toString())
                    }

                    is Resource.Loading -> {
                        Log.e("TAG6", ISSPositionDataState().toString())
                        issPos = issPos.copy(
                            isLoading = true
                        )
                        Log.e("TAG7", ISSPositionDataState().toString())
                        Log.e(TAG, ISSPositionDataState().toString())
                    }

                    is Resource.Error -> {
                        Log.e("TAG8", ISSPositionDataState().toString())
                        issPos = issPos.copy(
                            error = response.message!!
                        )
                        Log.e("TAG9", ISSPositionDataState().toString())
                        Log.e(TAG, ISSPositionDataState().toString())
                    }
                }
            }
        }
    }

    fun getISSPositionDetail(lat: Double, lng: Double) {
        viewModelScope.launch {
            issPositionDetailUseCase.getISSPositionDetail(lat, lng).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _issPositionDetail.value = ISSPositionDetailDataState(
                            isLoading = false,
                            issPositionDetail = response.data!!
                        )
                    }

                    is Resource.Loading -> {
                        _issPositionDetail.value = ISSPositionDetailDataState(
                            isLoading = true
                        )
                    }

                    is Resource.Error -> {
                        _issPositionDetail.value = ISSPositionDetailDataState(
                            isLoading = true,
                            error = response.message
                        )
                    }
                }
            }
        }
    }

    fun getISSTLES() {
        viewModelScope.launch {
            issTLESUseCase.getISSTLES().collect { response ->
                when (response) {

                    is Resource.Success -> {
                        Log.e(TAG, ISSTLESDataState().toString())
                        _issTLES.value = ISSTLESDataState(
                            isLoading = false,
                            tles = response.data?.body().toString()
                        )
                    }

                    is Resource.Loading -> {
                        Log.e(TAG, ISSTLESDataState().toString())
                        _issTLES.value = ISSTLESDataState(
                            isLoading = true
                        )
                    }

                    is Resource.Error -> {
                        _issTLES.value = ISSTLESDataState(
                            isLoading = true,
                            error = response.message.toString()
                        )
                    }
                }
            }
        }
    }
}