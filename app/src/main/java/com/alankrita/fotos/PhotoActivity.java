package com.alankrita.fotos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.alankrita.fotos.adapter.PhotoAdapter;
import com.alankrita.fotos.viewmodel.PhotoViewModel;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotoActivity extends AppCompatActivity {

    @BindView(R.id.photoRV)
    RecyclerView photoRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);
        PhotoViewModel mainActivityVM = ViewModelProviders.of(this).get(PhotoViewModel.class);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        photoRV.setLayoutManager(llm);
        PhotoAdapter photoAdapter = new PhotoAdapter(Glide.with(this));
        photoRV.setAdapter(photoAdapter);

        mainActivityVM.getPagedListLiveData().observe(this, photos -> {
            photoAdapter.submitList(photos);
            photoRV.setAdapter(photoAdapter);
        });
    }
}
