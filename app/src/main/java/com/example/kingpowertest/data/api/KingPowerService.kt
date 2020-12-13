package com.example.kingpowertest.data.api

import com.example.kingpowertest.BuildConfig
import com.example.kingpowertest.BuildConfig.BASE_URL
import com.example.kingpowertest.data.model.PhotoNetworkModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface KingPowerService {

    @GET("{albumId}/photos")
    suspend fun getPhotos(@Path("albumId") albumId: Int): List<PhotoNetworkModel>


    companion object{
        val client = OkHttpClient().newBuilder()
//            .addInterceptor(TokenExpiredInterceptor())
            .build()
        val instance: KingPowerService by lazy {
            Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(KingPowerService::class.java)
        }
    }


}