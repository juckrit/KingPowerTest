package com.example.kingpowertest.domain.repository

import com.example.kingpowertest.data.model.PhotoNetworkModel

interface PhotoRepository {

    suspend fun getPhotos(albumId:Int):List<PhotoNetworkModel>
}