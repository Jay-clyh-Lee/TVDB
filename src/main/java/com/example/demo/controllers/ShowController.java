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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.User;
import com.example.demo.models.Show;
import com.example.demo.services.UserService;
import com.example.demo.services.ShowService;


@Controller
public class ShowController {
	
	@Autowired
	private ShowService showService;

	@Autowired
	private UserService userService;
	// READ
	// view all Shows
	@GetMapping("/shows")
	public String viewAllShows(
			@ModelAttribute("user") User user,
			Model model
			) {

		model.addAttribute("shows", this.showService.getAll());
		return "shows.jsp"; // view all Shows page
	}
	
	// view a Show by id
	@GetMapping("/shows/{id}")
	public String viewShowById(
			@PathVariable Long id,
			Model model,
			HttpSession session
			) {
		
		User user = this.userService.retrieveCurrentUser(session);
		model.addAttribute("user_id", user.getId());
		
		model.addAttribute("show", this.showService.retrieve(id));
		model.addAttribute("poster", this.showService.retrieve(id).getUser());
		return "viewShow.jsp";
	}
	
	// CREATE
	// add user page
	@GetMapping("/shows/new")
	public String add(@ModelAttribute("show") Show show, Model model) {
		
		model.addAttribute("users", this.userService.all());
		return "newShow.jsp";
	}
	
	@PostMapping("/shows/create")
	public String create(
			@Valid @ModelAttribute("show") Show show,
			BindingResult result,
			RedirectAttributes redirectAttributes,
			HttpSession session
			) {
		
		User user = this.userService.retrieveCurrentUser(session);
		if ( user == null ) return "redirect:/";
		
		show.setUser(user);
		
		if ( !result.hasErrors() ) {
			
			show = this.showService.create(show);
			
			if ( show == null ) return "redirect:/shows/new";
			
			redirectAttributes.addFlashAttribute("message", "Your show has been Added");
		}		
		return "redirect:/shows";
	}
		
	// UPDATE
	// edit a user page
	@GetMapping("/shows/{id}/edit")
	public String edituserByIdFormPage(
			@PathVariable Long id,
			Model model
			) {
			
		model.addAttribute("show", this.showService.retrieve(id));
		
		return "editShow.jsp";
	}
		
	@PostMapping("/shows/update")
	public String update(  // what if this has the same name in showService.java
			@Valid @ModelAttribute("show") Show show,
			BindingResult result,
			RedirectAttributes redirectAttributes
				) {
		
		if ( result.hasErrors() ) return "editShow.jsp";
		
		this.showService.update(show); 
		
		redirectAttributes.addFlashAttribute("message", "This Show has been updated");
		
		return String.format("redirect:/Show/%d", show.getId()); // view the updated Show
	}
		
	//DELETE
	@GetMapping("/shows/delete/{id}")
	public String delete(
			@PathVariable Long id,
			RedirectAttributes redirectAttributes,
			HttpSession session
			) {
		
		User user = this.userService.retrieveCurrentUser(session);
		if ( user == null ) return "redirect:/";
		
		Show show = this.showService.retrieve(id);
		if ( show == null || show.getUser().getId() != user.getId() ) {
			
			redirectAttributes.addFlashAttribute("message", "You can't do this.");
			return "redirect:/shows";
		}
			
		this.showService.delete(id);
			
		redirectAttributes.addFlashAttribute("message", "This show has been deleted");
			
		return "redirect:/shows";
	}
	
}
