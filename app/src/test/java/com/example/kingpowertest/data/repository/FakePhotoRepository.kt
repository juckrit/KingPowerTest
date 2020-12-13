package com.example.kingpowertest.data.repository

import com.example.kingpowertest.data.model.PhotoNetworkModel
import com.example.kingpowertest.data.model.PhotoRoomModel
import com.example.kingpowertest.domain.repository.PhotoRepository

class FakePhotoRepository:PhotoRepository {

    private val photos = mutableListOf<PhotoNetworkModel>()

    init {
        photos.add(PhotoNetworkModel(1, 1, "title1", "url1", "t_url1"))
        photos.add(PhotoNetworkModel(1, 2, "title2", "url2", "t_url2"))
    }


    override suspend fun getPhotos(albumId: Int): List<PhotoNetworkModel> {
        return photos
    }

    override suspend fun savePhotos(photos: List<PhotoRoomModel>) {
        TODO("Not yet implemented")
    }
}