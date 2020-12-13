package com.example.kingpowertest.domain.usecase

import com.example.kingpowertest.data.model.PhotoNetworkModel
import com.example.kingpowertest.domain.repository.PhotoRepository

class GetPhotoUseCase(
    private val photoRepository: PhotoRepository
) {
    suspend fun execute(albumId:Int):List<PhotoNetworkModel>{
        return photoRepository.getPhotos(albumId)
    }
}