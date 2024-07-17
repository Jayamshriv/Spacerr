package com.example.antrikshgyan.domain.usecase.nasa

import android.util.Log
import com.example.antrikshgyan.common.Resource
import com.example.antrikshgyan.data.remote.dto.nasa.toAPODModel
import com.example.antrikshgyan.domain.model.nasa.APODModel
import com.example.antrikshgyan.domain.repository.NASARepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class APODByCountUseCase @Inject constructor(
    private val repository: NASARepository
) {
    private val TAG = "APODByCountUseCase"

    fun getAPODByCount(count : Int) =
        flow<Resource<MutableList<APODModel>>> {

            try {
                emit(Resource.Loading())
                val apodList = mutableListOf<APODModel>()
                repository.getAPODByCount(count).forEach {item->
                    apodList.add(item.toAPODModel())
                }
                Log.e(TAG, apodList.toString())
                emit(Resource.Success(data = apodList))
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