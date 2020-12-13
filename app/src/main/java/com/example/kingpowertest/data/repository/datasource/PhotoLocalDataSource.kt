package com.example.kingpowertest.data.repository.datasource

import com.example.kingpowertest.data.model.PhotoNetworkModel
import com.example.kingpowertest.data.model.PhotoRoomModel

interface PhotoLocalDataSource {
    suspend fun getPhotosFromDB():List<PhotoRoomModel>
    suspend fun savePhotosToDB(photos: List<PhotoRoomModel>)
    suspend fun clearAll()
}