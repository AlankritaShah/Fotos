package com.alankrita.fotos.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.alankrita.fotos.datasource.PhotoDataSource;
import com.alankrita.fotos.datasource.PhotoDataSourceFactory;
import com.alankrita.fotos.model.Photos;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class PhotoViewModel extends AndroidViewModel {
    private PhotoDataSourceFactory photoDataSourceFactory;
    //private MutableLiveData<PhotoDataSource> dataSourcePhotoLiveData;
    private Executor executor;
    private LiveData<PagedList<Photos>> pagedListPhotoLiveData;

    public PhotoViewModel(@NonNull Application application) {
        super(application);
        photoDataSourceFactory = new PhotoDataSourceFactory();
        //dataSourcePhotoLiveData = photoDataSourceFactory.getPhotoLiveData();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();
        executor = Executors.newFixedThreadPool(5);
        pagedListPhotoLiveData = (new LivePagedListBuilder<Long,Photos>(photoDataSourceFactory,config))
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<PagedList<Photos>> getPagedListLiveData() {
        return pagedListPhotoLiveData;
    }
}
