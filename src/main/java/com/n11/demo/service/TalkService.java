package com.n11.demo.service;

import java.util.List;
import com.n11.demo.model.Talk;

/**
 *
 * @author HOME
 */

public interface TalkService{
    
    public Talk add(Talk talk);
    
    public Talk remove(Talk talk);
    
    public List<Talk> getAllTalks();
    
    public Talk update(Talk talk);
    
    public Talk getById(int id);
    
}
