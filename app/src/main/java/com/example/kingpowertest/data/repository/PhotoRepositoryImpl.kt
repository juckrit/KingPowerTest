package com.example.kingpowertest.data.repository

import com.example.kingpowertest.data.model.PhotoModel
import com.example.kingpowertest.data.repository.datasourceImpl.PhotoRemoteDataSourceImpl
import com.example.kingpowertest.domain.repository.PhotoRepository

class PhotoRepositoryImpl(
    private val photoRemoteDataSourceImpl: PhotoRemoteDataSourceImpl
) : PhotoRepository {
    override suspend fun getPhotos(albumId: Int): List<PhotoModel> {
        return photoRemoteDataSourceImpl.getPhotos(albumId)
    }
}