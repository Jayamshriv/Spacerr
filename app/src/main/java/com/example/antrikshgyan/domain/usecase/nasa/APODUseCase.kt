package com.example.antrikshgyan.domain.usecase.nasa

import android.util.Log
import com.example.antrikshgyan.common.Resource
import com.example.antrikshgyan.data.remote.dto.nasa.toAPODModel
import com.example.antrikshgyan.domain.model.APODModel
import com.example.antrikshgyan.domain.repository.NASARepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class APODUseCase @Inject constructor(
    private val repository : NASARepository
){
    val TAG = "APOD"
    fun getAPOD() = flow<Resource<APODModel>> {

        try {

            emit(Resource.Loading<APODModel>())
            val data = repository.getAPODData()
            Log.e(TAG,data.toString())
            emit(Resource.Success<APODModel>(data.toAPODModel()))

            }catch(e: HttpException) {
            Log.e(TAG, "Unknown exception: ${e.message}")
            emit(Resource.Error<APODModel>(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            Log.e(TAG, "Unknown exception: ${e.message}")
            emit(Resource.Error<APODModel>("Couldn't reach server. Check your internet connection."))
        }catch (e: Exception) {
            Log.e(TAG, "Unknown exception: ${e.message}")
            emit(Resource.Error<APODModel>("An unknown error occurred"))

        }
    }
}