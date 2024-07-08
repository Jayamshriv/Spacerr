package com.example.antrikshgyan.domain.usecase.isro

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.example.antrikshgyan.common.Resource
import com.example.antrikshgyan.data.remote.dto.isro.Centre
import com.example.antrikshgyan.data.remote.dto.isro.CustomerSatellite
import com.example.antrikshgyan.data.remote.dto.isro.ISROCusSatellitesDto
import com.example.antrikshgyan.data.remote.dto.isro.toCentreModel
import com.example.antrikshgyan.data.remote.dto.isro.toCustomerSatelliteModel
import com.example.antrikshgyan.domain.model.CentreModel
import com.example.antrikshgyan.domain.model.CustomerSatelliteModel
import com.example.antrikshgyan.domain.repository.ISROVercelRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ISROVercelUseCase @Inject constructor(
    private val repository: ISROVercelRepository
) {
    private val TAG= "ISROVercelUseCase"
    private val customerSatelliteList =  mutableListOf<CustomerSatelliteModel>()
    private val centresList =  mutableListOf<CentreModel>()

    fun getCusSatellites() = flow<Resource<MutableList<CustomerSatelliteModel>>> {
        try{
            emit(Resource.Loading(data = null))
                repository.getCusSatellites().customer_satellites.forEach {item ->
                    customerSatelliteList.add(item.toCustomerSatelliteModel())
            }
                emit(Resource.Success(data = customerSatelliteList))

        }catch (e: HttpException) {
            Log.e(TAG, "Unknown Http exception: ${e.message}")
            Log.e(TAG, "Unknown Http exception: ${e.response()?.errorBody()}")
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            Log.e(TAG, "Unknown IO exception: ${e.message}")
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            Log.e(TAG, "Unknown Other exception: ${e.message}")
            emit(Resource.Error("An unknown error occurred"))
        }
    }

    fun getCentres() = flow<Resource<MutableList<CentreModel>>> {
        try{
            emit(Resource.Loading(data = null))
            repository.getCentres().centres.forEach {item ->
                centresList.add(item.toCentreModel())
            }
            emit(Resource.Success(data = centresList))

        }catch (e: HttpException) {
            Log.e(TAG, "Unknown Http exception: ${e.message}")
            Log.e(TAG, "Unknown Http exception: ${e.response()?.errorBody()}")
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            Log.e(TAG, "Unknown IO exception: ${e.message}")
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        } catch (e: Exception) {
            Log.e(TAG, "Unknown Other exception: ${e.message}")
            emit(Resource.Error("An unknown error occurred"))
        }
    }


}