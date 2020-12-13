package com.example.kingpowertest.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.kingpowertest.domain.repository.PhotoRepository
import com.example.kingpowertest.presentation.main.viewmodel.MainFragmentViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(AndroidJUnit4::class)
class GetPhotoUseCaseTest{

    private lateinit var getPhotoUseCase: GetPhotoUseCase
    private lateinit var photoRepository: PhotoRepository

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        photoRepository = Mockito.mock(PhotoRepository::class.java)
        getPhotoUseCase = GetPhotoUseCase(photoRepository)
    }

    @Test
    fun photoRepository_getPhotos_was_called(){
        runBlocking {
            getPhotoUseCase.execute(1)
            Mockito.verify(photoRepository, Mockito.times(1)).getPhotos(1);
        }
    }



}