package com.alankrita.fotos.datasource;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import com.alankrita.fotos.model.PhotoResponse;
import com.alankrita.fotos.model.Photos;
import com.alankrita.fotos.network.ApiService;
import com.alankrita.fotos.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoDataSource extends PageKeyedDataSource<Long, Photos> {

    private ApiService apiService;
    private String TAG = PhotoDataSource.class.getSimpleName();
    private String searchKey;

    public PhotoDataSource(String searchKey) {
        this.searchKey = searchKey;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Photos> callback) {

        apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
        Call<PhotoResponse> photoData = apiService.fetchPhotos(1, searchKey);
        photoData.enqueue(new Callback<PhotoResponse>() {
            @Override
            public void onResponse(Call<PhotoResponse> call, Response<PhotoResponse> response) {
                PhotoResponse photoResponse = response.body();
                //Log.i(TAG, "loadInitial photoSearch= " + photoSearch.getPhotos().getPage() + " " + photoSearch.getPhotos().getPhoto().get(0).getId());
                if(photoResponse.getPhotos() != null)
                    callback.onResult(photoResponse.getPhotos().getPhoto(),null,(long)2);
            }

            @Override
            public void onFailure(Call<PhotoResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Photos> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Photos> callback) {
        apiService = RetrofitInstance.getRetrofitInstance().create(ApiService.class);
        Call<PhotoResponse> photoData = apiService.fetchPhotos(params.key, searchKey);
        photoData.enqueue(new Callback<PhotoResponse>() {
            @Override
            public void onResponse(Call<PhotoResponse> call, Response<PhotoResponse> response) {
                PhotoResponse photoResponse = response.body();
                if(photoResponse.getPhotos() != null)
                    callback.onResult(photoResponse.getPhotos().getPhoto(), params.key+1);
            }

            @Override
            public void onFailure(Call<PhotoResponse> call, Throwable t) {

            }
        });
    }
}
