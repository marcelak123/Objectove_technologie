package com.example.uloha_8_2;

public class Film  {
    private final int id;
    private final String title;
    private final int length;
    private final int year;
    private final String link;

    public Film(int id, String title, int length, int year, String link) {
        this.id = id;
        this.title = title;
        this.length = length;
        this.year = year;
        this.link = link;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getLength() { return length; }
    public int getYear() { return year; }
    public String getLink() { return link; }
}

