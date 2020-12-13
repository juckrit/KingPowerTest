package com.example.kingpowertest.data.repository

import android.util.Log
import com.example.kingpowertest.data.model.PhotoNetworkModel
import com.example.kingpowertest.data.model.PhotoRoomModel
import com.example.kingpowertest.data.repository.datasource.PhotoLocalDataSource
import com.example.kingpowertest.data.repository.datasource.PhotoRemoteDataSource
import com.example.kingpowertest.data.repository.datasourceImpl.PhotoLocalDataSourceImpl
import com.example.kingpowertest.data.repository.datasourceImpl.PhotoRemoteDataSourceImpl
import com.example.kingpowertest.domain.repository.PhotoRepository

class PhotoRepositoryImpl(
    private val photoRemoteDataSource: PhotoRemoteDataSource,
    private val photoLocalDataSource: PhotoLocalDataSource
) : PhotoRepository {

    override suspend fun getPhotos(albumId: Int): List<PhotoNetworkModel> {
        val result = getPhotosFromDB(albumId)
        val photoRoomModels = result.map {
            PhotoRoomModel(it.photoId, it.albumId, it.title, it.url, it.thumbnailUrl)
        }
        val photoNetworkModels = result.map {
            PhotoNetworkModel(it.photoId, it.albumId, it.title, it.url, it.thumbnailUrl)
        }
        savePhotos(photoRoomModels)
        return photoNetworkModels
    }

    override suspend fun savePhotos(photos: List<PhotoRoomModel>) {
        photoLocalDataSource.clearAll()
        photoLocalDataSource.savePhotosToDB(photos)
    }

    suspend fun getPhotosFromDB(albumId: Int):List<PhotoRoomModel>{
        lateinit var photoList: List<PhotoRoomModel>
        try {
            photoList = photoLocalDataSource.getPhotosFromDB()
        } catch (exception: java.lang.Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        if(photoList.size>0){
            return photoList
        }else{
            photoList= getPhotosFromAPI(albumId).map {
                PhotoRoomModel(it.photoId, it.albumId, it.title, it.url, it.thumbnailUrl)
            }
            photoLocalDataSource.savePhotosToDB(photoList)
        }

        return photoList
    }

    suspend fun getPhotosFromAPI(albumId: Int): List<PhotoNetworkModel> {
        lateinit var photoList: List<PhotoNetworkModel>
        try {
            photoList = photoRemoteDataSource.getPhotos(albumId)
        } catch (exception: Exception) {
            Log.i("MyTag", exception.message.toString())
        }
        return photoList
    }
}