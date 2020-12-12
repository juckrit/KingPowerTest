package com.example.kingpowertest.presentation.main.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kingpowertest.FAILURE
import com.example.kingpowertest.data.model.PhotoModel
import com.example.kingpowertest.data.model.ResultWrapper
import com.example.kingpowertest.domain.usecase.GetPhotoUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class MainFragmentViewModel(
    private val getPhotoUseCase: GetPhotoUseCase
) : ViewModel() {

    private val photoListLiveData = MutableLiveData<ResultWrapper<List<PhotoModel>>>()

    fun getPhotoListLiveData() = photoListLiveData

    fun getPhotoByAlbumId(albumId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    getPhotoUseCase.execute(albumId)
                if (response != null) {
                    // success
                    photoListLiveData.postValue(ResultWrapper.Success(response))
                } else {
                    //Fail
                    photoListLiveData.postValue(ResultWrapper.Error(FAILURE, null))
                }
            } catch (e: IOException) {
                Log.d("TEST",e.message ?: "error")
                photoListLiveData.postValue(ResultWrapper.Error(e.message ?: "", e))

            } catch (e: Exception) {
                Log.d("TEST",e.message ?: "error")
                photoListLiveData.postValue(ResultWrapper.Error(FAILURE, e))

            }
        }
    }
}