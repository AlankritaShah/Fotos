package com.alankrita.fotos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.alankrita.fotos.adapter.PhotoAdapter;
import com.alankrita.fotos.model.Photos;
import com.alankrita.fotos.viewmodel.PhotoViewModel;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoActivity extends AppCompatActivity {

    private String TAG = PhotoActivity.class.getSimpleName();
    private PhotoViewModel photoViewModel;
    private PhotoAdapter photoAdapter;

    @BindView(R.id.photoRV)
    RecyclerView photoRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);
        photoViewModel = ViewModelProviders.of(this).get(PhotoViewModel.class);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        photoRV.setLayoutManager(llm);
        photoAdapter = new PhotoAdapter(Glide.with(this));
        photoViewModel.getPagedListLiveData().observe(this, photos -> {
            photoAdapter.submitList(photos);
            photoRV.setAdapter(photoAdapter);
        });

        performSearch("Dunzo");
    }

    private void performSearch(String searchKey) {
        photoViewModel.filterTextAll.setValue(searchKey);
    }
}
