package com.example.kingpowertest.presentation.main.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoPresentationModel(
    val url: String,
    val thumbnailUrl: String,
    val title: String
): Parcelable