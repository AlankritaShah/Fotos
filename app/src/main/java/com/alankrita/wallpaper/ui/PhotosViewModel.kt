package com.alankrita.wallpaper.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alankrita.wallpaper.domain.Photo
import com.alankrita.wallpaper.network.RetrofitClient
import kotlinx.coroutines.launch

class PhotosViewModel : ViewModel() {

    suspend fun fetchImages(searchTerm: String): List<Photo> {
        if (searchTerm.isBlank()) {
            return emptyList()
        }
        val searchResponse = RetrofitClient.client.fetchImages(searchTerm)
        return searchResponse.photos.photo.map { photo ->
            Photo(
                id = photo.id,
                url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                title = photo.title
            )
        }
    }
}