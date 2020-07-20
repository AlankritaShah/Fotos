package com.alankrita.wallpaper.network

import com.alankrita.wallpaper.data.PhotosSearchResponse
import com.alankrita.wallpaper.BuildConfig.FLICKR_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApiService {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&api_key=$FLICKR_API_KEY")
    suspend fun fetchImages(@Query(value = "text") searchTerm: String): PhotosSearchResponse
}