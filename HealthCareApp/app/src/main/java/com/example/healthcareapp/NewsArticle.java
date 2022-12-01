package com.example.healthcareapp;

import android.content.Context;
import android.content.res.Resources;

public class NewsArticle {
    private String newsTitle, newsContent, newsURL;
    private final int headerImage;
    Context context;

    public NewsArticle(String newsTitle, String newsContent, String newsURL, int headerImage) {
        this.headerImage = headerImage;
        this.newsContent = newsContent;
        this.newsURL = newsURL;
        this.newsTitle = newsTitle;
    }

    // TODO: This constructor only serves as a test. Remove this in the future
    public NewsArticle(Context context, String newsTitle) {
        this.headerImage = R.mipmap.ic_placeholder;
        this.newsContent = context.getString(R.string.lorem_ipsum_text);
        this.newsURL = "https://google.com";
        this.newsTitle = newsTitle;
        this.context = context;
    }

    public int getHeaderImage() {
        return headerImage;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public String getNewsURL() {
        return newsURL;
    }
}
