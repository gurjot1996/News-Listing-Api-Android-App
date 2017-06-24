package com.example.android.newslistingapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gurjot on 6/12/17.
 */

public class newsAdapter extends ArrayAdapter<news> {
    public newsAdapter(Context context, ArrayList<news> arr) {
        super(context, 0, arr);
    }

    @NonNull
    @Override
    public View getView(int position, View ListView, ViewGroup parent) {
        if (ListView == null) {
            ListView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        news currobj = (news) getItem(position);

        TextView t1 = (TextView) ListView.findViewById(R.id.sectionname);
        t1.setText(currobj.getSectionName());

        TextView t2 = (TextView) ListView.findViewById(R.id.news_title);
        t2.setText(currobj.getNews_title());

        TextView t3 = (TextView) ListView.findViewById(R.id.link);
        t3.setText(currobj.getUrl());
        return ListView;
    }
}
