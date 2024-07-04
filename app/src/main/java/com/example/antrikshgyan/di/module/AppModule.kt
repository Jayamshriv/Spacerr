package com.example.antrikshgyan.di.module

import android.util.Log
import com.example.antrikshgyan.constants.Constants
import com.example.antrikshgyan.data.remote.apiservice.APIService
import com.example.antrikshgyan.data.repository.NASARepositoryImpl
import com.example.antrikshgyan.domain.repository.NASARepository
import com.example.antrikshgyan.domain.usecase.apod.APODUseCase
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
    fun providesNASARepository(apiService: APIService): NASARepository{
        return NASARepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun providesAPODUseCase(repository :NASARepository) :APODUseCase{
        return APODUseCase(repository)
    }


}