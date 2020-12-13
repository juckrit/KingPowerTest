package com.example.kingpowertest.presentation.di

import androidx.room.Room
import com.example.kingpowertest.*
import com.example.kingpowertest.data.api.KingPowerService
import com.example.kingpowertest.data.db.KingPowerDatabase
import com.example.kingpowertest.data.db.PhotoDao
import com.example.kingpowertest.data.repository.PhotoRepositoryImpl
import com.example.kingpowertest.data.repository.datasource.PhotoLocalDataSource
import com.example.kingpowertest.data.repository.datasource.PhotoRemoteDataSource
import com.example.kingpowertest.data.repository.datasourceImpl.PhotoLocalDataSourceImpl
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

    single<KingPowerService>(named(DI_NAME_KingPowerService)) {
//        val client = OkHttpClient().newBuilder()
////            .addInterceptor(TokenExpiredInterceptor())
//            .build()
//        val instance: KingPowerService by lazy {
//            Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
//                .client(client)
//                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
//                .build()
//                .create(KingPowerService::class.java)
//        }
//        instance
        KingPowerService.instance
    }

    single<PhotoRemoteDataSource>(named(DI_NAME_PhotoRemoteDataSourceImpl)) {
        PhotoRemoteDataSourceImpl(get(named(DI_NAME_KingPowerService)))
    }

    single(named(DI_NAME_KingPowerDatabase)) {
        Room.databaseBuilder(get(), KingPowerDatabase::class.java, Database_KingPowerDatabase)
            .build()
    }

    single<PhotoDao>(named(DI_NAME_PhotoDao)) {
        val database = get<KingPowerDatabase>(named(DI_NAME_KingPowerDatabase))
        database.photoDao() as PhotoDao
    }

    single<PhotoLocalDataSource>(named(DI_NAME_PhotoLocalDataSourceImpl)) {
        PhotoLocalDataSourceImpl(get(named(DI_NAME_PhotoDao)))
    }

    single<PhotoRepository>(named(DI_NAME_PhotoRepository)) {
        PhotoRepositoryImpl(
            get(named(DI_NAME_PhotoRemoteDataSourceImpl)),
            get(named(DI_NAME_PhotoLocalDataSourceImpl))
        )
    }

    single(named(DI_NAME_GetPhotoUseCase)) {
        GetPhotoUseCase(get(named(DI_NAME_PhotoRepository)))
    }


    viewModel {
        MainFragmentViewModel(get(named(DI_NAME_GetPhotoUseCase)))
    }
}