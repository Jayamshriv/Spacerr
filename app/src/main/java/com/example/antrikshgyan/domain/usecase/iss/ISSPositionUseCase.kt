package com.example.antrikshgyan.domain.usecase.iss

import android.util.Log
import com.example.antrikshgyan.common.Resource
import com.example.antrikshgyan.data.remote.dto.iss.toISSPositionModel
import com.example.antrikshgyan.data.repository.iss.ISSPositionRepository
import com.example.antrikshgyan.domain.model.iss.ISSPositionModel
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ISSPositionUseCase @Inject constructor(
    private val repository: ISSPositionRepository
) {
    private val TAG = "ISSPositionUseCase"

    fun getISSPosition(units : String) = flow<Resource<ISSPositionModel>> {
        try {
            emit(Resource.Loading<ISSPositionModel>())
            Log.e("TAG1", "position.toString()")
            val position = repository.getISSPosition(units).toISSPositionModel()
            Log.e("TAG", position.toString())
            emit(Resource.Success<ISSPositionModel>(data = position))
            Log.e("TAG2", position.toString())
        }catch (e: HttpException){
            Log.e("TAG", e.message.toString())
            Log.e(TAG, "Unknown Http exception: ${e.response()?.errorBody()}")
        }catch (e: IOException) {
            Log.e(TAG, "Unknown IO exception: ${e.message}")
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            Log.e(TAG, "Unknown Other exception: ${e.message}")
            emit(Resource.Error("An unknown error occurred"))

        }
    }

}