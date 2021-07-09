package com.pactise.finalapp;

import java.util.ArrayList;

public class NewsModel {
    private ArrayList<Article> articles;

    public ArrayList<Article> getArticles() {
        return articles;
    }
}
class Article {
    private String title ;
    private String url;
    private String urlToImage;
    private String author;

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getAuthor() {
        return author;
    }
}
