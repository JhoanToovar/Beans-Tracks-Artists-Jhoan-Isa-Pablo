package com.service;

import com.model.Artist;
import com.model.Track;
import com.repository.ArtistRepositoryImpl;
import com.repository.IArtistRepository;
import com.repository.ITrackRepository;
import com.repository.TrackRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

public class ArtistService implements IArtistService {

    private ITrackRepository trackRepository;
    private IArtistRepository artistRepository;

    public ArtistService(IArtistRepository artistRepository, ITrackRepository trackRepository) {
        this.artistRepository = artistRepository;
        this.trackRepository = trackRepository;
    }

    @Override
    public void addArtist(Artist artist) {
        artistRepository.save(artist);
    }

    @Override
    public Collection<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    @Override
    public Artist findArtistByName(String name) {

        return artistRepository.findByName(name);
    }

    @Override
    public boolean deleteArtist(int id) {
        Artist removed = artistRepository.deleteById(id);
        if (removed == null) {
            return false;
        }
        for (Track track : removed.getTracks()) {
            track.getArtists().remove(removed);
        }
        return true;
    }
}
