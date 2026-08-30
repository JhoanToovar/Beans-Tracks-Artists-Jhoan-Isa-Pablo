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

@Service
public class TrackService implements ITrackService {

    @Autowired
    private ITrackRepository trackRepository;
    @Autowired
    private IArtistRepository artistRepository;

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
                artist.addTrack(savedTrack);
                savedTrack.addArtist(artist);
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
        for (Artist artist : removed.getArtists()) {
            artist.getTracks().remove(removed);
        }
        return true;
    }
}
