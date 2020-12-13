package com.example.kingpowertest.data.repository.datasourceImpl

import com.example.kingpowertest.data.api.KingPowerService
import com.example.kingpowertest.data.model.PhotoNetworkModel
import com.example.kingpowertest.data.repository.datasource.PhotoRemoteDataSource

class PhotoRemoteDataSourceImpl(
    private val kingPowerService: KingPowerService
) : PhotoRemoteDataSource {
    override suspend fun getPhotos(albumId: Int): List<PhotoNetworkModel> {
        return kingPowerService.getPhotos(albumId)
    }
}