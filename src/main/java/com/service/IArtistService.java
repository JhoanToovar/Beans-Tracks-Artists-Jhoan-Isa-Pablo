package com.service;

import com.model.Artist;
import com.model.Track;

import java.util.Collection;
import java.util.List;

public interface IArtistService {
    Collection<Artist> getAllArtists();
    void addArtist(Artist artist);
    Artist findArtistByName(String name);
    boolean deleteArtist(int id);
    List<Track> getTracksByArtist(int artistId);
}
