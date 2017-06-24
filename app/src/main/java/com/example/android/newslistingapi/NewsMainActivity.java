package com.example.android.newslistingapi;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NewsMainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<news>> {
    public static final String urlApi = "https://content.guardianapis.com/";
    public static String str = "technology";
    private static int Loader_id = 1;
    public static final String api_key = "?api-key=test";
    private static int flag = 0;
    private static ArrayList<news> arrayList = null;

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return (netInfo != null && netInfo.isConnected());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView l1 = (ListView) findViewById(R.id.list);
        final TextView t1 = (TextView) findViewById(R.id.empty);
        if (!isOnline(NewsMainActivity.this)) {
            Toast.makeText(NewsMainActivity.this, "NO INTERNET CONNECTION", Toast.LENGTH_SHORT).show();
            l1.setVisibility(View.INVISIBLE);
            t1.setText("NO INTERNET CONNECTION");
            Toast.makeText(this, "Restart the App", Toast.LENGTH_SHORT).show();
            getLoaderManager().destroyLoader(Loader_id);
            return;
        }
        TextView tech = (TextView) findViewById(R.id.tech);
        tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnline(NewsMainActivity.this) == false) {
                    Toast.makeText(NewsMainActivity.this, "NO INTERNET CONNECTION", Toast.LENGTH_SHORT).show();
                    l1.setVisibility(View.GONE);
                    getLoaderManager().destroyLoader(Loader_id);
                    return;
                }
                str = "technology";
                getSupportLoaderManager().initLoader(Loader_id, null, NewsMainActivity.this).forceLoad();
            }
        });
        TextView business = (TextView) findViewById(R.id.business);
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnline(NewsMainActivity.this) == false) {
                    Toast.makeText(NewsMainActivity.this, "NO INTERNET CONNECTION", Toast.LENGTH_SHORT).show();
                    l1.setVisibility(View.INVISIBLE);
                    getLoaderManager().destroyLoader(Loader_id);
                    return;
                }
                str = "business";
                getSupportLoaderManager().initLoader(Loader_id, null, NewsMainActivity.this).forceLoad();
            }
        });
        TextView music = (TextView) findViewById(R.id.music);
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnline(NewsMainActivity.this) == false) {
                    Toast.makeText(NewsMainActivity.this, "NO INTERNET CONNECTION", Toast.LENGTH_SHORT).show();
                    l1.setVisibility(View.INVISIBLE);
                    getLoaderManager().destroyLoader(Loader_id);
                    return;
                }
                str = "music";
                getSupportLoaderManager().initLoader(Loader_id, null, NewsMainActivity.this).forceLoad();
            }
        });
        TextView poli = (TextView) findViewById(R.id.politics);
        poli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOnline(NewsMainActivity.this) == false) {
                    l1.setVisibility(View.INVISIBLE);
                    return;
                }
                str = "politics";
                getSupportLoaderManager().initLoader(Loader_id, null, NewsMainActivity.this).forceLoad();
            }
        });
        ListView listView = (ListView) findViewById(R.id.list);
        final newsAdapter adap = new newsAdapter(this, arrayList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                news curr = (news) adapterView.getItemAtPosition(pos);
                String final_url = curr.getUrl();
                Intent id = new Intent(Intent.ACTION_VIEW);
                id.setData(Uri.parse(final_url));
                startActivity(id);
            }
        });
        getSupportLoaderManager().initLoader(Loader_id, null, this).forceLoad();
    }

    public static String getString() {
        return (urlApi + str + api_key);
    }

    @Override
    public Loader<ArrayList<news>> onCreateLoader(int id, Bundle args) {
        return new newsLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<news>> loader, ArrayList<news> data) {
        arrayList = data;
        ListView listView = (ListView) findViewById(R.id.list);
        newsAdapter adapter = new newsAdapter(this, data);
        listView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<news>> loader) {
        loader.reset();
    }
}
