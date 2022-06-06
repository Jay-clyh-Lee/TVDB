package com.example.demo.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private UserRepository repository;
	
	@GetMapping("/")
	public String index() {
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String getLoginForm(@ModelAttribute("user") User user) {
		return "login_reg.jsp";
	}
	
	@PostMapping("/login")
	public String login(
			@Valid @ModelAttribute("user") User user,
			RedirectAttributes redirectAttributes,
			HttpSession session,
			Model model
			) {
		
		user = this.service.authenticate(user);
		if ( user != null ) {
			
			session.setAttribute("user_id", user.getId());
			session.setAttribute("user_name", user.getName());
			
			redirectAttributes.addFlashAttribute("message", String.format("Welcome back %s!", user.getEmail()));
			
			return "redirect:/shows";
		}
		
		redirectAttributes.addFlashAttribute("message", "Invalid Credentials");
		model.addAttribute("message", "Invalid Credentials");
		
		return "login_reg.jsp";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.removeAttribute("user");
		
		return "redirect:/login";
	}
	
	@GetMapping("/register")
	public String getRegisterForm(@ModelAttribute("user") User user) {
		return "login_reg.jsp";
	}
	
	@PostMapping("/register")
	public String register(
			@Valid @ModelAttribute("user") User user,
			BindingResult result,
			RedirectAttributes redirectAttributes,
			HttpSession session,
			Model model
			) {
		
		session.setAttribute("user_id", user.getId());
		session.setAttribute("user_name", user.getName());
		
		if ( ! user.getPassword().equals(user.getPasswordConfirmation()) ) {
			result.rejectValue("passwordConfirmation", "Matches", "The passwords must match!!");
		}
		
		if ( this.repository.findByEmail(user.getEmail()).isPresent() ) {
        	result.rejectValue("email", "Email", "That email address is not available.");
        }
		
		if ( result.hasErrors() ) {
			
			redirectAttributes.addFlashAttribute("message", "There is an error!");
			return "login_reg.jsp";
		}
			
		this.service.create(user);
		
		redirectAttributes.addFlashAttribute("message", "Thank you for registering!");
		
		return "redirect:/shows";
	}
	
}