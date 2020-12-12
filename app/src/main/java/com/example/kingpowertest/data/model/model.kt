package com.example.kingpowertest.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PhotoModel(
    @SerializedName("albumId") @Expose val albumId: Int,
    @SerializedName("id") @Expose val photoId: Int,
    @SerializedName("title") @Expose val title: String,
    @SerializedName("url") @Expose val url: String,
    @SerializedName("thumbnailUrl") @Expose val thumbnailUrl: String
)