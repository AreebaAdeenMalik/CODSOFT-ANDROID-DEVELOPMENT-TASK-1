package com.example.quotesapplication;

public class QuoteResponse {
    String text = "";
    String author = "";

     boolean isFavorite = false;

    public QuoteResponse(String text, String author) {
        this.text = text;
        this.author = author;
    }

    public boolean getFavorite() {
        return isFavorite;
    }
    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getText() {
        return text;
    }
    public String getAuthor() {
        return author;
    }
}
