package com.service;

import com.model.Artist;
import com.model.Track;
import com.repository.IArtistRepository;
import com.repository.ITrackRepository;

import java.util.ArrayList;
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
        for (Integer trackId : removed.getTrackIds()) {
            Track track = trackRepository.findById(trackId);
            if (track != null) {
                track.removeArtistId(id);
            }
        }
        return true;
    }

    @Override
    public List<Track> getTracksByArtist(int artistId) {
        Artist artist = artistRepository.findById(artistId);
        if (artist == null) {
            return List.of();
        }
        List<Track> tracks = new ArrayList<>();
        for (Integer trackId : artist.getTrackIds()) {
            Track track = trackRepository.findById(trackId);
            if (track != null) {
                tracks.add(track);
            }
        }
        return tracks;
    }
}
