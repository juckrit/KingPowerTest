package com.example.kingpowertest.domain.repository

import com.example.kingpowertest.data.model.PhotoNetworkModel
import com.example.kingpowertest.data.model.PhotoRoomModel

interface PhotoRepository {

    suspend fun getPhotos(albumId:Int):List<PhotoNetworkModel>
    suspend fun savePhotos(photos:List<PhotoRoomModel>)
}