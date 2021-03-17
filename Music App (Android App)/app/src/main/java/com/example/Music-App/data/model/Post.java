package com.example.youtbe.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Post {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("artist")
    @Expose
    private String artist;
    @SerializedName("mood")
    @Expose
    private String mood;
    @SerializedName("song")
    @Expose
    private String song;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", mood='" + mood + '\'' +
                ", song='" + song + '\'' +
                '}';
    }
}
