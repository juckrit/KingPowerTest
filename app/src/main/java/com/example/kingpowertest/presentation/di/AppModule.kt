package com.example.kingpowertest.presentation.di

import com.example.kingpowertest.*
import com.example.kingpowertest.data.api.KingPowerService
import com.example.kingpowertest.data.repository.PhotoRepositoryImpl
import com.example.kingpowertest.data.repository.datasourceImpl.PhotoRemoteDataSourceImpl
import com.example.kingpowertest.domain.repository.PhotoRepository
import com.example.kingpowertest.domain.usecase.GetPhotoUseCase
import com.example.kingpowertest.presentation.main.viewmodel.MainFragmentViewModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single(named(DI_NAME_KingPowerService)) {
        val client = OkHttpClient().newBuilder()
//            .addInterceptor(TokenExpiredInterceptor())
            .build()
        val instance: KingPowerService by lazy {
            Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(KingPowerService::class.java)
        }
        instance
    }

    single(named(DI_NAME_PhotoRemoteDataSourceImpl)) {
        PhotoRemoteDataSourceImpl(get(named(DI_NAME_KingPowerService)))
    }

    single<PhotoRepository>(named(DI_NAME_PhotoRepository)) {
        PhotoRepositoryImpl(get(named(DI_NAME_PhotoRemoteDataSourceImpl)))
    }

    single(named(DI_NAME_GetPhotoUseCase)) {
        GetPhotoUseCase(get(named(DI_NAME_PhotoRepository)))
    }


    viewModel {
        MainFragmentViewModel(get(named(DI_NAME_GetPhotoUseCase)))
    }
}