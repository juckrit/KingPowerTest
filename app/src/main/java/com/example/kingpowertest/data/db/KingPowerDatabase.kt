package com.example.kingpowertest.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kingpowertest.data.model.PhotoRoomModel

@Database(
    entities = [PhotoRoomModel::class], version = 1, exportSchema = false
)
abstract class KingPowerDatabase : RoomDatabase() {

    abstract fun photoDao(): PhotoDao
}