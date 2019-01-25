package com.n11.demo.service;

import java.util.List;


import com.n11.demo.model.Track;

public interface TrackService {
	
    public Track add(Track talk);
    
    public List<Track> getAllTracks();
    
    public Track create();
    
    public void deleteAll();

}
