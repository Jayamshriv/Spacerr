package com.example.antrikshgyan.domain.usecase.iss

import android.util.Log
import com.example.antrikshgyan.common.Resource
import com.example.antrikshgyan.data.repository.iss.ISSPositionRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class ISSTLESUseCase @Inject constructor(
    private val repository: ISSPositionRepository
) {

    private val TAG = "ISSTLESUseCase"
    fun getISSTLES() = flow<Resource<Response<String>>> {
        try {
            emit(Resource.Loading())
            val tles = repository.getISSTLES()
            emit(Resource.Success(data = tles))
        } catch (e: HttpException) {
            Log.e(TAG, e.message.toString())
            emit(Resource.Error("Unknown Http exception : ${e.message.toString()}"))
        } catch (e: IOException) {
            Log.e(TAG, e.message.toString())
            emit(Resource.Error("Unknown IO exception : ${e.message.toString()}"))
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
            emit(Resource.Error("Unknown IO exception : ${e.message.toString()}"))
        }
    }
}