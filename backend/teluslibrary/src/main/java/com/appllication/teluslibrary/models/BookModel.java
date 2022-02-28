package com.appllication.teluslibrary.models;
public class BookModel
{
    private String title;
    private int stock;

    public BookModel(String title, int stock) {
        this.title = title;
        this.stock = stock;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}