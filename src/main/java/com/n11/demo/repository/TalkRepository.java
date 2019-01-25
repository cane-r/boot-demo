package com.n11.demo.repository;

import java.util.List;
import java.util.Optional;

import com.n11.demo.model.Talk;


public interface TalkRepository {

	
    public Optional<Talk> add(Talk talk);
    
    public Optional<Talk> remove(Talk talk);
    
    public Optional<List<Talk>> getAllTalks();
    
    public Optional<Talk> update(Talk talk);
    
    public Optional<Talk> getById(int id);
	
}
