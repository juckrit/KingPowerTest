package com.example.kingpowertest.domain.repository

import com.example.kingpowertest.data.model.PhotoModel

interface PhotoRepository {

    suspend fun getPhotos(albumId:Int):List<PhotoModel>
}