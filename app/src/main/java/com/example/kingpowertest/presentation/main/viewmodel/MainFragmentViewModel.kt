package com.example.kingpowertest.presentation.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kingpowertest.FAILURE
import com.example.kingpowertest.data.model.ResultWrapper
import com.example.kingpowertest.domain.usecase.GetPhotoUseCase
import com.example.kingpowertest.presentation.main.mapper.PhotoNetworkModelMapper
import com.example.kingpowertest.presentation.main.model.PhotoPresentationModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class MainFragmentViewModel(
    private val getPhotoUseCase: GetPhotoUseCase,
    private val mapper: PhotoNetworkModelMapper
) : ViewModel() {

    private val photoListLiveData = MutableLiveData<ResultWrapper<List<PhotoPresentationModel>>>()

    fun getPhotoListLiveData() = photoListLiveData

    fun getPhotoByAlbumId(albumId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    getPhotoUseCase.execute(albumId)
                if (response != null) {
                    // success
                    val allProducts = response.map {
                        PhotoPresentationModel(it.url, it.thumbnailUrl, it.title)
                    } ?: emptyList()
                    photoListLiveData.postValue(ResultWrapper.Success(allProducts))
                } else {
                    //Fail
                    photoListLiveData.postValue(ResultWrapper.Error(FAILURE, null))
                }
            } catch (e: IOException) {
                Log.d("TEST", e.message ?: "error")
                photoListLiveData.postValue(ResultWrapper.Error(e.message ?: "", e))

            } catch (e: Exception) {
                Log.d("TEST", e.message ?: "error")
                photoListLiveData.postValue(ResultWrapper.Error(FAILURE, e))

            }
        }
    }
}