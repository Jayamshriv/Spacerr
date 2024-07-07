package com.example.antrikshgyan.domain.usecase.isro

import android.util.Log
import com.example.antrikshgyan.common.Resource
import com.example.antrikshgyan.data.remote.dto.isro.ISROSpacecraftDto
import com.example.antrikshgyan.data.remote.dto.isro.toISROSpacecraftModel
import com.example.antrikshgyan.domain.model.APODModel
import com.example.antrikshgyan.domain.model.ISROSpaceCraftModel
import com.example.antrikshgyan.domain.repository.ISROServiceRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class ISROServiceUseCase @Inject constructor(
    private val repository: ISROServiceRepository
){
    val TAG = "Spacecaft"
    val spacecraftList = mutableListOf<ISROSpaceCraftModel>()
    fun getISROSpacecraft() = flow<Resource<MutableList<ISROSpaceCraftModel>>>{

        try {
            emit(Resource.Loading())
            val data = repository.getISROSpacecraft()
//                .map { it.toISROSpacecraftModel()}
            data.body()?.forEach {item ->
                spacecraftList.add(item.toISROSpacecraftModel())
            }
            Log.e(TAG,data.toString())
            emit(Resource.Success(data = spacecraftList))
        }catch(e: HttpException) {
            Log.e(TAG, "Unknown Http exception: ${e.message}")
            Log.e(TAG, "Unknown Http exception: ${e.response()?.errorBody()}")
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch(e: IOException) {
            Log.e(TAG, "Unknown IO exception: ${e.message}")
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }catch (e: Exception) {
            Log.e(TAG, "Unknown Other exception: ${e.message}")
            emit(Resource.Error("An unknown error occurred"))

        }

    }
}