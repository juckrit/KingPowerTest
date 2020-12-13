package com.example.kingpowertest.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.kingpowertest.data.model.PhotoRoomModel
import com.example.kingpowertest.data.repository.datasource.PhotoLocalDataSource
import com.example.kingpowertest.data.repository.datasource.PhotoRemoteDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class PhotoRepositoryImplTest {
    private lateinit var mockPhotoRemoteDataSource: PhotoRemoteDataSource
    private lateinit var mockPhotoLocalDataSource: PhotoLocalDataSource
    private lateinit var photoRepositoryImpl: PhotoRepositoryImpl

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockPhotoRemoteDataSource = Mockito.mock(PhotoRemoteDataSource::class.java)
        mockPhotoLocalDataSource = Mockito.mock(PhotoLocalDataSource::class.java)
        photoRepositoryImpl =
            PhotoRepositoryImpl(mockPhotoRemoteDataSource, mockPhotoLocalDataSource)
    }

    @Test
    fun getPhotos_wasCalled_when_getPhotosFromDB_return_zero() {
        runBlocking {
            Mockito.`when`(mockPhotoLocalDataSource.getPhotosFromDB()).thenReturn(emptyList())
            Mockito.`when`(mockPhotoRemoteDataSource.getPhotos(1)).thenReturn(emptyList())
            photoRepositoryImpl.getPhotos(1)
            Mockito.verify(mockPhotoRemoteDataSource, Mockito.times(1)).getPhotos(1)

        }
    }

    @Test
    fun getPhotos_wasNotCalled_when_getPhotosFromDB_NotReturn_zero() {
        runBlocking {
            Mockito.`when`(mockPhotoLocalDataSource.getPhotosFromDB()).thenReturn(
                listOf(
                    PhotoRoomModel(1, 1, "title1", "url1", "t_url1")
                )
            )
            Mockito.`when`(mockPhotoRemoteDataSource.getPhotos(1)).thenReturn(emptyList())
            photoRepositoryImpl.getPhotos(1)
            Mockito.verify(mockPhotoRemoteDataSource, Mockito.times(0)).getPhotos(1)

        }
    }
}