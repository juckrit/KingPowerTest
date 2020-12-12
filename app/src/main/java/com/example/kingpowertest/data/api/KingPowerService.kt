package com.example.kingpowertest.data.api

import com.example.kingpowertest.data.model.PhotoModel
import retrofit2.http.GET
import retrofit2.http.Path

interface KingPowerService {

    @GET("{albumId}/photos")
    suspend fun getPhotos(@Path("albumId") albumId: Int): List<PhotoModel>

}