package com.example.kingpowertest.domain.usecase

import com.example.kingpowertest.data.model.PhotoModel
import com.example.kingpowertest.domain.repository.PhotoRepository

class GetPhotoUseCase(
    private val photoRepository: PhotoRepository
) {
    suspend fun execute(albumId:Int):List<PhotoModel>{
        return photoRepository.getPhotos(albumId)
    }
}