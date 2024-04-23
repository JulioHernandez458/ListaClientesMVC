package com.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login(@RequestParam(value="error", required=false) String error,
			@RequestParam(value="logout", required=false) String logout,
			Model model, Principal principal, RedirectAttributes flash) {
		
		if(principal != null) {
			flash.addFlashAttribute("info","You're already logged in!");
			return "redirect:/";
		}
		
		if(error != null) {
			model.addAttribute("error","Username or Password Incorrect! Try Again.");
		}
		
		if(logout != null) {
			model.addAttribute("success","Logout Successful!");
		}
		
		model.addAttribute("title", "Sign in");
		return "login";
	}
	
}
