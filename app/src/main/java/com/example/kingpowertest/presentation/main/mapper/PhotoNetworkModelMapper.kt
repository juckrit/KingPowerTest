package com.example.kingpowertest.presentation.main.mapper

import com.example.kingpowertest.data.model.PhotoNetworkModel
import com.example.kingpowertest.presentation.Mapper
import com.example.kingpowertest.presentation.main.model.PhotoPresentationModel

class PhotoNetworkModelMapper : Mapper<PhotoNetworkModel, PhotoPresentationModel> {
    override fun map(from: PhotoNetworkModel): PhotoPresentationModel = PhotoPresentationModel(
        url = from.url,
        thumbnailUrl = from.thumbnailUrl,
        title = from.title
    )


}