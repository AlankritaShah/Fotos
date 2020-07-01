package com.alankrita.fotos.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class Photos {
    private String id, secret, server, title;
    private int farm;

    public Photos(String id, String secret, String server, String title, int farm) {
        this.id = id;
        this.secret = secret;
        this.server = server;
        this.title = title;
        this.farm = farm;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return "https://farm"+farm+".staticflickr.com/"+server+"/"+id+"_"+secret+"_m.jpg";
    }

    public String getId() {
        return id;
    }
}