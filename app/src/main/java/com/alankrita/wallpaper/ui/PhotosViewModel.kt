package com.alankrita.wallpaper.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alankrita.wallpaper.domain.Photo
import com.alankrita.wallpaper.network.Client
import kotlinx.coroutines.launch

class PhotosViewModel : ViewModel() {
    private val mutablePhotosLiveData = MutableLiveData<List<Photo>>()
    val photosLiveData: LiveData<List<Photo>> = mutablePhotosLiveData

    init {
        viewModelScope.launch {
            val searchResponse = Client.client.fetchImages()
            val photosList = searchResponse.photos.photo.map { photo ->
                Photo(
                    id = photo.id,
                    url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                    title = photo.title
                )
            }
            mutablePhotosLiveData.postValue(photosList)
        }
    }
}