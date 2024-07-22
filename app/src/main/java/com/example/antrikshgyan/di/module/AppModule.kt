package com.example.antrikshgyan.di.module

import android.util.Log
import com.example.antrikshgyan.common.Constants
import com.example.antrikshgyan.data.remote.apiservice.APIService
import com.example.antrikshgyan.data.remote.apiservice.ISSApiService
import com.example.antrikshgyan.data.remote.apiservice.IsroServiceApiService
import com.example.antrikshgyan.data.remote.apiservice.IsroVercelApiService
import com.example.antrikshgyan.data.repository.isro.ISROServiceRepositoryImpl
import com.example.antrikshgyan.data.repository.isro.ISROVercelRepositoryImpl
import com.example.antrikshgyan.data.repository.iss.ISSPositionRepository
import com.example.antrikshgyan.data.repository.nasa.NASARepositoryImpl
import com.example.antrikshgyan.domain.repository.isro.ISROServiceRepository
import com.example.antrikshgyan.domain.repository.isro.ISROVercelRepository
import com.example.antrikshgyan.domain.repository.iss.ISSPositionRepositoryImpl
import com.example.antrikshgyan.domain.repository.nasa.NASARepository
import com.example.antrikshgyan.domain.usecase.isro.ISROServiceUseCase
import com.example.antrikshgyan.domain.usecase.isro.ISROVercelUseCase
import com.example.antrikshgyan.domain.usecase.iss.ISSPositionDetailUseCase
import com.example.antrikshgyan.domain.usecase.iss.ISSPositionUseCase
import com.example.antrikshgyan.domain.usecase.iss.ISSTLESUseCase
import com.example.antrikshgyan.domain.usecase.nasa.APODByCountUseCase
import com.example.antrikshgyan.domain.usecase.nasa.APODUseCase
import com.example.antrikshgyan.domain.usecase.nasa.MarsRoverImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
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
        return try {
            Retrofit.Builder()
                .baseUrl(Constants.BASE_URL_ISRO_SERVICES)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IsroServiceApiService::class.java)
        } catch (e: Exception) {
            throw IllegalStateException("Failed to get Isro services ")
        }

    }

    @Provides
    @Singleton
    fun providesISROServiceRepository(isroServiceApiService: IsroServiceApiService): ISROServiceRepository {
        return ISROServiceRepositoryImpl(isroServiceApiService)
    }

    @Provides
    @Singleton
    fun providesISROUseCase(isroServiceRepository: ISROServiceRepository): ISROServiceUseCase {
        return ISROServiceUseCase(isroServiceRepository)
    }

    //--------------------------------------------ISRO Vercel API ------------------------------------------------------//

    @Provides
    @Singleton
    fun providesISROVercelAPi(): IsroVercelApiService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL_ISRO_VERCEL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IsroVercelApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesISROVercelRepository(apiService: IsroVercelApiService): ISROVercelRepository {
        return ISROVercelRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun providesISROVercelUseCase(repository: ISROVercelRepository): ISROVercelUseCase {
        return ISROVercelUseCase(repository)
    }

    //------------------------------Mars UseCase------------------

    @Provides
    @Singleton
    fun providesMarsRoverImageUseCase(repository: NASARepository): MarsRoverImageUseCase {
        return MarsRoverImageUseCase(repository)
    }

    //---------------------------APOD By Count----------------------------------

    @Provides
    @Singleton
    fun providesAPODByCountUseCase(repository: NASARepository): APODByCountUseCase {
        return APODByCountUseCase(repository)
    }

    //----------------------------ISS---------------------------------------------
    @Provides
    @Singleton
    fun providesISSApiService(): ISSApiService {
        return try {
            Retrofit
                .Builder()
                .baseUrl(Constants.BASE_URL_ISS)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ISSApiService::class.java)
        } catch (e: Exception) {
            Log.e("Error", e.toString())
            throw IllegalStateException("Failed to create Nasa instance", e)
        }
    }
    @Provides
    @Singleton
    fun providesISSPositionRepository(apiService: ISSApiService) : ISSPositionRepository{
        return ISSPositionRepositoryImpl(apiService = apiService)
    }

    @Provides
    @Singleton
    fun providesISSPositionUseCase(repository: ISSPositionRepository) : ISSPositionUseCase {
        return ISSPositionUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesISSPositionDetailUseCase(repository: ISSPositionRepository) : ISSPositionDetailUseCase {
        return ISSPositionDetailUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesISSTLESUseCase(repository: ISSPositionRepository) : ISSTLESUseCase {
        return ISSTLESUseCase(repository)
    }

}