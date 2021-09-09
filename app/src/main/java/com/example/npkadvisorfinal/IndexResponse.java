package com.example.npkadvisorfinal;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class IndexResponse {

    @SerializedName("datos")
    @Expose
    private ArrayList<IndexResponse2> InfoIndex;

    public ArrayList<IndexResponse2> getInfoIndex() {
        return InfoIndex;
    }

        public void setInfoIndex(ArrayList<IndexResponse2> Variables) {
        this.InfoIndex = Variables;
    }

    @NonNull
    @Override
    public String toString() {
        return "datos{" +
                "datos=" + InfoIndex +
                '}';
    }
}
