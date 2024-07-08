package com.example.antrikshgyan.presentation.isro_zone.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.antrikshgyan.common.Resource
import com.example.antrikshgyan.domain.usecase.isro.ISROVercelUseCase
import com.example.antrikshgyan.presentation.isro_zone.state.ISROCentresDataState
import com.example.antrikshgyan.presentation.isro_zone.state.ISROCusSatellitesDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ISROVercelViewModel @Inject constructor(
    private val useCase: ISROVercelUseCase
) : ViewModel() {

    private val _customerSatellitesState = mutableStateOf(ISROCusSatellitesDataState())
    val customerSatelliteState: State<ISROCusSatellitesDataState> = _customerSatellitesState

    private val _centreState = mutableStateOf(ISROCentresDataState())
    val centreState: State<ISROCentresDataState> = _centreState

    init {
        getCusSatellites()
        getCentres()
    }

    private fun getCusSatellites() {
        viewModelScope.launch {
            useCase.getCusSatellites().collect { customerSatellite ->
                when(customerSatellite){
                    is Resource.Success->{
                        _customerSatellitesState.value = ISROCusSatellitesDataState(
                            customerSatellite = customerSatellite.data!!
                        )
                    }
                    is Resource.Error -> {
                        _customerSatellitesState.value = ISROCusSatellitesDataState(
                            isLoading = true,
                            error = customerSatellite.message!!
                        )
                    }
                    is Resource.Loading -> {
                        _customerSatellitesState.value = ISROCusSatellitesDataState(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }

    private fun getCentres() {
        viewModelScope.launch {
            useCase.getCentres().collect { centres ->
                when(centres){
                    is Resource.Success->{
                        _centreState.value = ISROCentresDataState(
                            centres = centres.data!!
                        )
                    }
                    is Resource.Error -> {
                        _centreState.value = ISROCentresDataState(
                            isLoading = true,
                            error = centres.message!!
                        )
                    }
                    is Resource.Loading -> {
                        _centreState.value = ISROCentresDataState(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }


}