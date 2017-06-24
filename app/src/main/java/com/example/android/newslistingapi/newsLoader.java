package com.example.android.newslistingapi;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import java.util.ArrayList;

import static com.example.android.newslistingapi.NewsMainActivity.getString;

/**
 * Created by gurjot on 6/13/17.
 */

public class newsLoader extends AsyncTaskLoader<ArrayList<news>> {
    public newsLoader(Context context) {
        super(context);
    }

    @Override
    public ArrayList<news> loadInBackground() {
        ArrayList<news> arr = newsUtil.fetchNews(getString());
        return arr;
    }
}
