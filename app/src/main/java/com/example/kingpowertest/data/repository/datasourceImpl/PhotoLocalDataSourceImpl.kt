package com.example.kingpowertest.data.repository.datasourceImpl

import com.example.kingpowertest.data.db.PhotoDao
import com.example.kingpowertest.data.model.PhotoRoomModel
import com.example.kingpowertest.data.repository.datasource.PhotoLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhotoLocalDataSourceImpl(
    private val photoDao: PhotoDao
) : PhotoLocalDataSource {

    override suspend fun getPhotosFromDB(): List<PhotoRoomModel> {
        return photoDao.getPhotos()
    }

    override suspend fun savePhotosToDB(photos: List<PhotoRoomModel>) {
        CoroutineScope(Dispatchers.IO).launch {
            photoDao.savePhotos(photos)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            photoDao.deleteAllPhotos()
        }
    }
}