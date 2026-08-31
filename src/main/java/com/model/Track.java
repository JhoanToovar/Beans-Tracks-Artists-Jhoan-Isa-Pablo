package com.model;

import java.util.ArrayList;
import java.util.List;

public class Track {
    private int id;
    private String title;
    private String genre;
    private String duration;
    private String albumTitle;
    private List<Integer> artistIds = new ArrayList<>();

    public Track() {}

    public Track(int id, String title, String genre, String duration, String albumTitle) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.albumTitle = albumTitle;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getDuration() {
        return duration;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<Integer> getArtistIds() {
        return artistIds;
    }

    public void addArtistId(int artistId) {
        artistIds.add(artistId);
    }

    public void removeArtistId(int artistId) {
        artistIds.remove(Integer.valueOf(artistId));
    }
}
