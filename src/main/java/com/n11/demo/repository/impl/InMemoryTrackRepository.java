package com.n11.demo.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.n11.demo.model.Track;
import com.n11.demo.repository.TrackRepository;

@Repository
public class InMemoryTrackRepository implements TrackRepository {

	
	
    private List<Track> trackRepo;
	
    @Autowired
	public InMemoryTrackRepository(List<Track> trackRepo) {
		super();
		this.trackRepo = trackRepo;
	}

	@Override
	public Optional<Track> add(Track track) {
		trackRepo.add(track);
		return Optional.ofNullable(track);
	}

	@Override
	public Optional<List<Track>> getAllTracks() {
		return Optional.ofNullable(trackRepo);
	}

	@Override
	public void deleteAllTracks() {
		trackRepo.clear();
	}

}
