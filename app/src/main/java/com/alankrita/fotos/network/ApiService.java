package com.alankrita.fotos.network;

import com.alankrita.fotos.model.PhotoResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    String FLICKR_API_KEY = "cb187b7b8f60badc83bca4be4a1fb7d0";
    int page_size = 10;
    String query = "?method=flickr.photos.search&format=json&nojsoncallback=1&api_key="+FLICKR_API_KEY+"&per_page="+page_size;
    @GET(query)
    Call<PhotoResponse> fetchPhotos(@Query("page") long page, @Query("text") String searchText);
}
