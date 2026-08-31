package com.model;

import java.util.ArrayList;
import java.util.List;

public class Artist {
    private int id;
    private String name;
    private String nationality;
    private List<Integer> trackIds = new ArrayList<>();

    public Artist() {

    }

    public Artist(int id, String name, String nationality) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public List<Integer> getTrackIds() {
        return trackIds;
    }

    public void addTrackId(int trackId) {
        trackIds.add(trackId);
    }

    public void removeTrackId(int trackId) {
        trackIds.remove(Integer.valueOf(trackId));
    }
}
