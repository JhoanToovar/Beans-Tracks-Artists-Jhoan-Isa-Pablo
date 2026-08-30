package com.service;

import com.model.Artist;

import java.util.Collection;

public interface IArtistService {
    Collection<Artist> getAllArtists();
    void addArtist(Artist artist);
    Artist findArtistByName(String name);
    boolean deleteArtist(int id);
}
