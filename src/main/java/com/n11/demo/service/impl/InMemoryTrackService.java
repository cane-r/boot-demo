package com.n11.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n11.demo.model.Track;
import com.n11.demo.repository.TrackRepository;
import com.n11.demo.service.TrackService;

@Service
public class InMemoryTrackService implements TrackService {

	
	
    private TrackRepository trackRepo;
    private int counter=0;
	
    @Autowired
	public InMemoryTrackService(TrackRepository trackRepo) {
		this.trackRepo=trackRepo;
	}
	
	@Override
	public Track add(Track track) {
		track.setId(counter++);
		return trackRepo.add(track).get();
	}

	@Override
	public List<Track> getAllTracks() {
		// TODO Auto-generated method stub
		return trackRepo.getAllTracks().get();
	}

	@Override
	public Track create() {
		return add(new Track());
	}

	@Override
	public void deleteAll() {
		trackRepo.deleteAllTracks();
		counter=0;
		
	}
	
	

}
