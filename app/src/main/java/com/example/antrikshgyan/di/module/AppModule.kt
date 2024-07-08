package com.example.antrikshgyan.di.module

import android.util.Log
import com.example.antrikshgyan.common.Constants
import com.example.antrikshgyan.data.remote.apiservice.APIService
import com.example.antrikshgyan.data.remote.apiservice.IsroServiceApiService
import com.example.antrikshgyan.data.remote.apiservice.IsroVercelApiService
import com.example.antrikshgyan.data.repository.isro.ISROServiceRepositoryImpl
import com.example.antrikshgyan.data.repository.isro.ISROVercelRepositoryImpl
import com.example.antrikshgyan.data.repository.nasa.NASARepositoryImpl
import com.example.antrikshgyan.domain.repository.ISROServiceRepository
import com.example.antrikshgyan.domain.repository.ISROVercelRepository
import com.example.antrikshgyan.domain.repository.NASARepository
import com.example.antrikshgyan.domain.usecase.isro.ISROServiceUseCase
import com.example.antrikshgyan.domain.usecase.isro.ISROVercelUseCase
import com.example.antrikshgyan.domain.usecase.nasa.APODUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //--------------------------------------------APOD ------------------------------------------------------//
    @Provides
    @Singleton
    fun providesNASAapi(): APIService {
        return try {
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_NASA)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIService::class.java)
        } catch (e: Exception) {
            Log.e("Error", e.toString())
            throw IllegalStateException("Failed to create Nasa instance", e)
        }
    }

    @Provides
    @Singleton
    fun providesNASARepository(apiService: APIService): NASARepository {
        return NASARepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun providesAPODUseCase(repository: NASARepository): APODUseCase {
        return APODUseCase(repository)
    }

    //--------------------------------------------ISRO Services ------------------------------------------------------//

    @Provides
    @Singleton
    fun providesISROServicesAPI(): IsroServiceApiService {
        return try{
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_ISRO_SERVICES)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IsroServiceApiService::class.java)
        }catch (e : Exception){
            throw IllegalStateException("Failed to get Isro services ")
        }

    }

    @Provides
    @Singleton
    fun providesISROServiceRepository(isroServiceApiService: IsroServiceApiService): ISROServiceRepository{
        return ISROServiceRepositoryImpl(isroServiceApiService)
    }

    @Provides
    @Singleton
    fun providesISROUseCase(isroServiceRepository: ISROServiceRepository) : ISROServiceUseCase{
        return ISROServiceUseCase(isroServiceRepository)
    }

    //--------------------------------------------ISRO Vercel API ------------------------------------------------------//

    @Provides
    @Singleton
    fun providesISROVercelAPi() : IsroVercelApiService{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_ISRO_VERCEL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IsroVercelApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesISROVercelRepository(apiService: IsroVercelApiService) : ISROVercelRepository{
        return ISROVercelRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun providesISROVercelUseCase(repository: ISROVercelRepository) : ISROVercelUseCase{
        return ISROVercelUseCase(repository)
    }




}