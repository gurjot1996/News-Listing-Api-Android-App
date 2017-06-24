package com.example.android.newslistingapi;

/**
 * Created by gurjot on 6/12/17.
 */

public class news {
    String news_title;
    String sectionName;
    String url;

    news(String title, String sectionName, String webUrl) {
        this.news_title = title;
        this.sectionName = sectionName;
        this.url = webUrl;
    }

    public String getNews_title() {
        return news_title;
    }

    public String getSectionName() {
        return sectionName;
    }

    public String getUrl() {
        return url;
    }
}
