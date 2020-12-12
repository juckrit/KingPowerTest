package com.example.kingpowertest.data.repository.datasource

import com.example.kingpowertest.data.model.PhotoModel

interface PhotoRemoteDataSource {
    suspend fun getPhotos(albumId:Int):List<PhotoModel>
}