package com.example.antrikshgyan.domain.usecase.apod

import android.util.Log
import com.example.antrikshgyan.data.remote.dto.toAPODModel
import com.example.antrikshgyan.domain.model.APODModel
import com.example.antrikshgyan.domain.repository.NASARepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.Flow
import javax.inject.Inject

class APODUseCase @Inject constructor(
    private val repository : NASARepository
){
    val TAG = "APOD"
    fun getAPOD() = flow<APODModel> {

        try {
            val data = repository.getAPODData()
            Log.e(TAG,data.toString())
            emit(data.toAPODModel())

            }catch(e: HttpException) {
            Log.e(TAG, "Unknown exception: ${e.message}")
        } catch(e: IOException) {
            Log.e(TAG, "Unknown exception: ${e.message}")
        }catch (e: Exception) {
            Log.e(TAG, "Unknown exception: ${e.message}")

        }
    }
}