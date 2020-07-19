package com.alankrita.wallpaper.network

import com.alankrita.wallpaper.data.PhotosSearchResponse
import com.alankrita.wallpaper.BuildConfig.FLICKR_API_KEY
import retrofit2.http.GET

interface PhotoApiService {
    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&text=apple&api_key=$FLICKR_API_KEY")
    suspend fun fetchImages(): PhotosSearchResponse
}