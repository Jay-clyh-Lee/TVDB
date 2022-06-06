package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Show;
import com.example.demo.repositories.ShowRepository;


@Service
public class ShowService {
	
	@Autowired
	private ShowRepository repo;
	
    // CREATE
    // create a Show and link to a user
    public Show create(Show show) {
        return this.repo.save(show);   // with or without this, same result
    }
    
    // READ
    // return all Shows
    public List<Show> getAll() {
        return repo.findAll();
    }  
    
    // return a Show by id
    public Show retrieve(Long showId) {
    	return this.repo.findById(showId).get();
    }
    
    // UPDATE
    // update a Show by id
    public Show update(Show showId) {
    	return this.repo.save(showId);
    }
    
    // DELETE
    // delete a Show by id
    public void delete(Long showId) {
    	this.repo.deleteById(showId);
    }
    
}