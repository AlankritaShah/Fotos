package com.alankrita.fotos.network;

import com.alankrita.fotos.Config;
import com.alankrita.fotos.model.PhotoResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    String FLICKR_API_KEY = "062a6c0c49e4de1d78497d13a7dbb360";
    String query = "?method=flickr.photos.search&format=json&nojsoncallback=1&api_key="+FLICKR_API_KEY+"&per_page="+ Config.PAGE_SIZE;
    @GET(query)
    Call<PhotoResponse> fetchPhotos(@Query("page") long page, @Query("text") String searchText);
}
