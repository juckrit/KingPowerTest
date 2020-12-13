package com.example.kingpowertest.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.kingpowertest.data.model.PhotoNetworkModel
import com.example.kingpowertest.domain.repository.PhotoRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class GetPhotoUseCaseTest {

    private lateinit var getPhotoUseCase: GetPhotoUseCase
    private lateinit var mockPhotoRepository: PhotoRepository

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mockPhotoRepository = Mockito.mock(PhotoRepository::class.java)
        getPhotoUseCase = GetPhotoUseCase(mockPhotoRepository)
    }

    //check getPhotos inside PhotoRepository was called
    @Test
    fun photoRepository_getPhotos_was_called() {
        runBlocking {
            getPhotoUseCase.execute(1)
            Mockito.verify(mockPhotoRepository, Mockito.times(1)).getPhotos(1);
        }
    }

    //check execute inside GetPhotoUseCase return expectedResult
    @Test
    fun getPhotoUseCase_execute_returnExpectedResult() {
        runBlocking {
            val expectedResult = listOf(
                PhotoNetworkModel(1, 1, "title1", "url1", "t_url1"),
                PhotoNetworkModel(1, 2, "title2", "url2", "t_url2")
            )
            Mockito.`when`(mockPhotoRepository.getPhotos(1)).thenReturn(expectedResult)
            val result = getPhotoUseCase.execute(1)
            assertNotNull(result); //check if the object is != null
            assertEquals(expectedResult, result);
        }
    }


}