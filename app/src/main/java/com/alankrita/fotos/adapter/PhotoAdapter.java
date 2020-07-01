package com.alankrita.fotos.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.alankrita.fotos.R;
import com.alankrita.fotos.model.Photos;
import com.bumptech.glide.RequestManager;

public class PhotoAdapter extends PagedListAdapter<Photos, PhotoAdapter.PhotoVH> {
    private String TAG = PhotoAdapter.class.getSimpleName();
    private final RequestManager glide;
    public PhotoAdapter(RequestManager glide) {
        super(Photos.PHOTOS_DIFF_CALLBACK);
        this.glide = glide;
    }

    @NonNull
    @Override
    public PhotoVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View view = layoutInflater.inflate(R.layout.list_photos,viewGroup,false);
        return new PhotoVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoVH photoVH, int i) {
        photoVH.titleTV.setText(getItem(i).getTitle());
        glide.load(getItem(i).getUrl())
                .into(photoVH.photoIV);
    }

    static class PhotoVH extends RecyclerView.ViewHolder {
        ImageView photoIV;
        TextView titleTV;

        PhotoVH(@NonNull View itemView) {
            super(itemView);
            photoIV = itemView.findViewById(R.id.photoIV);
            titleTV = itemView.findViewById(R.id.titleTV);
        }
    }
}
