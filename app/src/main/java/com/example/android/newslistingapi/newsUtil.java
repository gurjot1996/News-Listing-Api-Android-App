package com.example.android.newslistingapi;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by gurjot on 6/12/17.
 */

public class newsUtil {
    public static String TAG = newsUtil.class.getName();

    public static ArrayList<news> fetchNews(String url) {
        String json = null;
        URL news_url = createurl(url);
        try {
            json = httpRequest(news_url);
        } catch (IOException e) {
            Log.e(TAG, "error", e);
        }
        ArrayList<news> arrayList = extractJson(json);
        return arrayList;
    }

    private static URL createurl(String url) {
        URL urlobj = null;
        try {
            if (url != null) {
                urlobj = new URL(url);
            }
        } catch (MalformedURLException e) {
            Log.e(TAG, "error", e);
        }
        return urlobj;
    }

    private static String httpRequest(URL url) throws IOException {
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        String jsonResponse = null;
        if (url == null) {
            return null;
        }
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = getJsonData(inputStream);
            }
        } catch (IOException e) {
            Log.e(TAG, "error", e);
        }
        return jsonResponse;
    }

    private static String getJsonData(InputStream inputStream) throws IOException {
        StringBuilder obj = new StringBuilder();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String a = bufferedReader.readLine();
            while (a != null) {
                obj.append(a);
                a = bufferedReader.readLine();
            }
        } catch (IOException e) {
            Log.e(TAG, "error", e);
        }
        return obj.toString();
    }

    private static ArrayList<news> extractJson(String json) {
        ArrayList<news> arr = new ArrayList<>();
        try {
            JSONObject root = new JSONObject(json);
            JSONObject response = root.getJSONObject("response");
            JSONArray results = response.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject o1 = results.getJSONObject(i);
                String sectionName = o1.getString("sectionName");
                String news = o1.getString("webTitle");
                String news_url = o1.getString("webUrl");
                arr.add(new news(news, sectionName, news_url));
            }
        } catch (JSONException e) {
            Log.e(TAG, "error", e);
        }
        return arr;
    }
}
