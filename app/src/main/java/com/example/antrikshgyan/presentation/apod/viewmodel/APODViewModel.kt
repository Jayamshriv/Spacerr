package com.example.antrikshgyan.presentation.apod.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.antrikshgyan.constants.Constants
import com.example.antrikshgyan.domain.model.APODModel
import com.example.antrikshgyan.domain.usecase.apod.APODUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class APODViewModel @Inject constructor(
    private val useCase : APODUseCase
) : ViewModel() {

    private val TAG = "APODViewModel"

    private val _state = mutableStateOf(APODModel())
    val state : State<APODModel> = _state

    init {
        getAPOD()
    }

    private fun getAPOD(){
        viewModelScope.launch {
            useCase.getAPOD().collect{ result ->
                if(result !=null) {
                    _state.value = APODModel(
                        title = result.title,
                        url = result.url,
                        media_type = result.media_type,
                        explanation = result.explanation,
                        hdurl = result.hdurl
                    )
                    Log.e(TAG,result.toString())
                }else{
                    Log.e(TAG,result)
                }
        }

        }
    }

}