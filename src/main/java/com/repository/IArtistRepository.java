package com.repository;

import com.model.Artist;

import java.util.Collection;

public interface IArtistRepository {
    Collection<Artist> findAll();
    void save(Artist artist);
    Artist findByName(String name);
    Artist findById(int id);
    Artist deleteById(int id);
}
