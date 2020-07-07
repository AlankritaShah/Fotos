package com.alankrita.fotos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

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

    @BindView(R.id.resultsTV)
    TextView resultsTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources()
                .getColor(R.color.bg_color)));

        photoViewModel = ViewModelProviders.of(this).get(PhotoViewModel.class);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        photoRV.setLayoutManager(llm);
        photoAdapter = new PhotoAdapter(Glide.with(this));
        photoRV.setAdapter(photoAdapter);

        photoViewModel.getPagedListLiveData().observe(this, photos -> {
            photoAdapter.submitList(photos);
        });

        performSearch("");
    }

    private void performSearch(String searchKey) {
        if(searchKey.isEmpty()){
            resultsTV.setVisibility(View.VISIBLE);
            searchKey = getString(R.string.defult_search);
        }
        else{
            resultsTV.setVisibility(View.GONE);
        }
        photoViewModel.filterTextAll.setValue(searchKey);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                performSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                performSearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
