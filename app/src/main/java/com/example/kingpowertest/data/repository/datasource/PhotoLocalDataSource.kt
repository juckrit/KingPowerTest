package com.example.kingpowertest.data.repository.datasource

import com.example.kingpowertest.data.model.PhotoNetworkModel

interface PhotoLocalDataSource {
    suspend fun getPhotoFromDB(albumId:Int):List<PhotoNetworkModel>
}