package com.example.antrikshgyan.domain.usecase.nasa

import android.util.Log
import com.example.antrikshgyan.common.Resource
import com.example.antrikshgyan.data.remote.dto.nasa.mars.toMarsRoverModel
import com.example.antrikshgyan.domain.model.nasa.APODModel
import com.example.antrikshgyan.domain.model.nasa.mars.MarsRoverModel
import com.example.antrikshgyan.domain.repository.NASARepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MarsRoverImageUseCase @Inject constructor(
    private val repository: NASARepository
) {

    val TAG = "MarsRoverImageUseCase"

    fun getMarsRoversImages(
        sol: Int,
        page: Int,
    )  = flow<Resource<MarsRoverModel>> {
        Log.d("API Calls", "Fetching Mars Rover Images for sol: $sol, page: $page")
        try {
            emit(Resource.Loading())
            val marsRoverModelResponse = repository.getMarsRoversImages(sol,page).toMarsRoverModel()
            Log.e(TAG, marsRoverModelResponse.toString())
            emit(Resource.Success(data = marsRoverModelResponse))
        }catch(e: HttpException) {
            Log.e(TAG, "Unknown exception: ${e.message}")
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            Log.e(TAG, "Unknown exception: ${e.message}")
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }catch (e: Exception) {
            Log.e(TAG, "Unknown exception: ${e.message}")
            emit(Resource.Error("An unknown error occurred"))
        }
    }
}