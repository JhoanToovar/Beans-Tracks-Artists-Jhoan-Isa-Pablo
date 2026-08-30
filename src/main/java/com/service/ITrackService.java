package com.service;

import com.model.Track;

import java.util.Collection;
import java.util.List;

public interface ITrackService {
    Collection<Track> getAllTracks();
    Track createTrack(Track track, List<Integer> artistIds);
    boolean deleteTrack(int trackId);
}
