package com.example.kingpowertest.presentation.di

import com.example.kingpowertest.DI_NAME_GetPhotoUseCase
import com.example.kingpowertest.DI_NAME_KingPowerService
import com.example.kingpowertest.DI_NAME_PhotoRemoteDataSourceImpl
import com.example.kingpowertest.DI_NAME_PhotoRepository
import com.example.kingpowertest.data.api.KingPowerService
import com.example.kingpowertest.data.repository.PhotoRepositoryImpl
import com.example.kingpowertest.data.repository.datasourceImpl.PhotoRemoteDataSourceImpl
import com.example.kingpowertest.domain.repository.PhotoRepository
import com.example.kingpowertest.domain.usecase.GetPhotoUseCase
import com.example.kingpowertest.presentation.main.viewmodel.MainFragmentViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    single(named(DI_NAME_KingPowerService)) {
        KingPowerService.instance
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