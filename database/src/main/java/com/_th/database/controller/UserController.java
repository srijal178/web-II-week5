package com._th.database.controller;



	import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.ModelAttribute;
	import org.springframework.web.bind.annotation.PostMapping;

import com._th.database.service.UserService;

	@Controller
	public class UserController {

	    private final UserService userService;

	    public UserController(UserService userService) {
	        this.userService = userService;
	    }

	    @GetMapping("/signup")
	    public String showSignupForm(Model model) {
	        model.addAttribute("user", new User());
	        return "signup";
	    }

	    @PostMapping("/signup")
	    public String signup(@ModelAttribute com._th.database.entity.User user) {
	        userService.save(user);
	        return "redirect:/login";
	    }

	    @GetMapping("/login")
	    public String showLoginForm() {
	        return "login";
	    }

	    @GetMapping("/home")
	    public String home() {
	        return "home";
	    }
	}

