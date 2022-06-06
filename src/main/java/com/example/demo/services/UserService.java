package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;


@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User>all() {
		return this.repository.findAll();
	}
	
	public User authenticate(User user) {
		
		Optional<User> foundUser = this.repository.findByEmail(user.getEmail());
		
		if( foundUser.isEmpty() ||
				! BCrypt.checkpw(user.getPassword(), foundUser.get().getPassword())
				) return null;
		
		return foundUser.get();
	}
	
	public User create(User user) {
		
		String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashedPassword);
		
		return this.repository.save(user);
	}
	
	public void delete(Long itemId) {
		this.repository.deleteById(itemId);
	}
	
	public User retrieve(Long itemId) {
		return this.repository.findById(itemId).get();
	}
	
	public User retrieveCurrentUser(HttpSession session) {
		
		if ( session.getAttribute("user") == null ) return null;
		
		return this.retrieve((Long) session.getAttribute("user"));
	}
	
	public User save(User item) {
		return this.repository.save(item);
	}
	
}