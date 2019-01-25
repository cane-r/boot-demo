package com.n11.demo.repository;

import java.util.List;
import java.util.Optional;
import com.n11.demo.model.Track;

/**
 *
 * @author HOME
 */

public interface TrackRepository {
    
    public Optional<Track> add(Track talk);
    
    public Optional<List<Track>> getAllTracks();
    
    public void deleteAllTracks();
    
}
    