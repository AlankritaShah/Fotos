package com.alankrita.fotos.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class PhotoDataSourceFactory extends DataSource.Factory {

    private PhotoDataSource photoDataSource;
    private MutableLiveData<PhotoDataSource> photoLiveData;

    public PhotoDataSourceFactory() {
        photoLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        photoDataSource = new PhotoDataSource();
        photoLiveData.postValue(photoDataSource);
        return photoDataSource;
    }

    public MutableLiveData<PhotoDataSource> getPhotoLiveData() {
        return photoLiveData;
    }
}
