package com.example.antrikshgyan.domain.usecase.iss

import android.util.Log
import com.example.antrikshgyan.common.Resource
import com.example.antrikshgyan.data.remote.dto.iss.toISSPositionDetailModel
import com.example.antrikshgyan.data.repository.iss.ISSPositionRepository
import com.example.antrikshgyan.domain.model.iss.ISSPositionDetailModel
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class ISSPositionDetailUseCase @Inject constructor(
    private val repository: ISSPositionRepository
) {
    private val TAG = "ISSPositionDetailUseCase"

     fun getISSPositionDetail(lat : Double, lng : Double) = flow<Resource<ISSPositionDetailModel>> {
        try {
            emit(Resource.Loading())
            val positionDetail = repository.getISSPositionDetail(lat, lng).toISSPositionDetailModel()
            emit(Resource.Success(positionDetail))
        }catch (e: HttpException){
            Log.e(TAG,e.message.toString())
            emit(Resource.Error("Unknown Http exception : ${e.message.toString()}"))
        }catch (e: IOException){
            Log.e(TAG,e.message.toString())
            emit(Resource.Error("Unknown IO exception : ${e.message.toString()}"))
        }catch (e: Exception){
            Log.e(TAG,e.message.toString())
            emit(Resource.Error("Unknown IO exception : ${e.message.toString()}"))
        }
    }

}