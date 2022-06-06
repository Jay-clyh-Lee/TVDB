package com.example.demo.repositories;

import java.util.*;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Show;

@Repository
public interface ShowRepository extends CrudRepository<Show, Long> {
	
	List<Show> findAll();
}