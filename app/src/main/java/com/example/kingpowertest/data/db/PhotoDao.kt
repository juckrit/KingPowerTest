package com.example.kingpowertest.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kingpowertest.data.model.PhotoRoomModel

@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePhotos(artists: List<PhotoRoomModel>)

    @Query(value = "DELETE FROM table_photos")
    suspend fun deleteAllPhotos()

    @Query(value = "SELECT * FROM table_photos")
    suspend fun getPhotos(): List<PhotoRoomModel>
}