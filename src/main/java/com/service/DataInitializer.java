package com.service;

import com.model.Artist;
import com.model.Track;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {

    private static final int TRACKS_PER_ARTIST = 5;

    private final IArtistService artistService;
    private final ITrackService trackService;

    @Autowired
    public DataInitializer(IArtistService artistService, ITrackService trackService) {
        this.artistService = artistService;
        this.trackService = trackService;
    }

    @PostConstruct
    private void linkArtistsAndTracks() {
        List<Artist> artists = new ArrayList<>(artistService.getAllArtists());
        List<Track> tracks = new ArrayList<>(trackService.getAllTracks());

        for (int i = 0; i < artists.size(); i++) {
            Artist artist = artists.get(i);
            for (int j = 0; j < TRACKS_PER_ARTIST; j++) {
                int trackIndex = i * TRACKS_PER_ARTIST + j;
                if (trackIndex >= tracks.size()) {
                    break;
                }
                Track track = tracks.get(trackIndex);
                artist.addTrackId(track.getId());
                track.addArtistId(artist.getId());
            }
        }
    }
}
