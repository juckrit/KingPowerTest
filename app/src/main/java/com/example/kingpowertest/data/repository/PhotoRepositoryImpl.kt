package com.example.kingpowertest.data.repository

import com.example.kingpowertest.data.model.PhotoNetworkModel
import com.example.kingpowertest.data.repository.datasourceImpl.PhotoRemoteDataSourceImpl
import com.example.kingpowertest.domain.repository.PhotoRepository

class PhotoRepositoryImpl(
    private val photoRemoteDataSourceImpl: PhotoRemoteDataSourceImpl
) : PhotoRepository {
    override suspend fun getPhotos(albumId: Int): List<PhotoNetworkModel> {
        return photoRemoteDataSourceImpl.getPhotos(albumId)
    }
}