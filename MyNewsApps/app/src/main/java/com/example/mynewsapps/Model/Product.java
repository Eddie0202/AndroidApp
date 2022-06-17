package com.example.mynewsapps.Model;

public class Product {
    private int id;
    private String title;
    private String shortdesc;
    private String image;
    private String content;
    private String date;
    private String author;

    public Product(int id, String title, String shortdesc, String image) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.image = image;
    }

    public Product(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Product(int id, String title, String shortdesc, String author, String image, String date) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.image = image;
        this.author = author;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



}
