package com.repository;

import com.model.Track;

import java.util.Collection;

public interface ITrackRepository {
    Collection<Track> getTracks();
    Track createTrack(Track track);
    Track deleteTrack(int trackId);
    Track findById(int id);
}
