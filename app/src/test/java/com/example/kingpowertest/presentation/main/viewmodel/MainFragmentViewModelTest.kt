package com.example.kingpowertest.presentation.main.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.kingpowertest.FAILURE
import com.example.kingpowertest.data.model.PhotoNetworkModel
import com.example.kingpowertest.data.model.ResultWrapper
import com.example.kingpowertest.data.repository.FakePhotoRepository
import com.example.kingpowertest.domain.repository.PhotoRepository
import com.example.kingpowertest.domain.usecase.GetPhotoUseCase
import com.example.kingpowertest.getOrAwaitValue
import com.example.kingpowertest.presentation.main.model.PhotoPresentationModel
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`


@RunWith(AndroidJUnit4::class)
class MainFragmentViewModelTest {
    private lateinit var viewModel: MainFragmentViewModel
    private lateinit var fakeRepository: PhotoRepository

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        fakeRepository = Mockito.mock(PhotoRepository::class.java)
        val getPhotoUseCase = GetPhotoUseCase(fakeRepository)
        viewModel = MainFragmentViewModel(getPhotoUseCase)
    }

    @Test
    fun getPhotos_ReturnPhotosExpected() {
        val mockList = listOf(
            PhotoNetworkModel(1, 1, "title1", "url1", "t_url1"),
            PhotoNetworkModel(1, 2, "title2", "url2", "t_url2")
        )
        runBlocking {
            `when`(fakeRepository.getPhotos(1)).thenReturn(mockList)
            val photosExpected = mutableListOf<PhotoPresentationModel>()
            photosExpected.add(PhotoPresentationModel("url1", "t_url1", "title1"))
            photosExpected.add(PhotoPresentationModel("url2", "t_url2", "title2"))

            viewModel.getPhotoByAlbumId(1)
            val currentList = viewModel.getPhotoListLiveData().getOrAwaitValue()
            when (currentList) {
                is ResultWrapper.Success -> {
                    Truth.assertThat((currentList.value)).isEqualTo(photosExpected)
                }
                is ResultWrapper.Error -> {
                    assertThat(currentList.cause).isNull()
                }
            }
        }

    }

    @Test
    fun fetchData_returnValueAsNull_Then_ShowMessageAs_FAILURE() {
        runBlocking {
            `when`(fakeRepository.getPhotos(1)).thenReturn(null)
            viewModel.getPhotoByAlbumId(1)
            val currentList = viewModel.getPhotoListLiveData().getOrAwaitValue()
            when (currentList) {
                is ResultWrapper.Success -> {
                    assertThat(currentList.value).isNull()
                }
                is ResultWrapper.Error -> {
                    assertThat(currentList.message).isEqualTo(FAILURE)
                }
            }
        }

    }

    @Test
    fun fetchData_ThrowsException_Then_CauseIsNotNull() {
        runBlocking {
            `when`(fakeRepository.getPhotos(1)).thenThrow(RuntimeException())
            viewModel.getPhotoByAlbumId(1)
            val currentList = viewModel.getPhotoListLiveData().getOrAwaitValue()
            when (currentList) {
                is ResultWrapper.Success -> {
                    assertThat(currentList.value).isNull()
                }
                is ResultWrapper.Error -> {
                    assertThat(currentList.cause).isNotNull()
                }
            }
        }

    }
}