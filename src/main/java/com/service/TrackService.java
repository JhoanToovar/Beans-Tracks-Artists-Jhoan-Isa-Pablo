package com.service;

import com.model.Artist;
import com.model.Track;
import com.repository.IArtistRepository;
import com.repository.ITrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class TrackService implements ITrackService {

    private final ITrackRepository trackRepository;
    private final IArtistRepository artistRepository;

    @Autowired
    public TrackService(ITrackRepository trackRepository, IArtistRepository artistRepository) {
        this.trackRepository = trackRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public Collection<Track> getAllTracks() {
        return trackRepository.getTracks();
    }

    @Override
    public Track createTrack(Track track, List<Integer> artistIds) {
        Track savedTrack = trackRepository.createTrack(track);
        for (Integer artistId : artistIds) {
            Artist artist = artistRepository.findById(artistId);
            if (artist != null) {
                artist.addTrackId(savedTrack.getId());
                savedTrack.addArtistId(artistId);
            }
        }
        return savedTrack;
    }

    @Override
    public boolean deleteTrack(int trackId) {
        Track removed = trackRepository.deleteTrack(trackId);
        if (removed == null) {
            return false;
        }
        for (Integer artistId : removed.getArtistIds()) {
            Artist artist = artistRepository.findById(artistId);
            if (artist != null) {
                artist.removeTrackId(trackId);
            }
        }
        return true;
    }

    @Override
    public List<Artist> getArtistsByTrack(int trackId) {
        Track track = trackRepository.findById(trackId);
        if (track == null) {
            return List.of();
        }
        List<Artist> artists = new ArrayList<>();
        for (Integer artistId : track.getArtistIds()) {
            Artist artist = artistRepository.findById(artistId);
            if (artist != null) {
                artists.add(artist);
            }
        }
        return artists;
    }
}
