package com.example.demo.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@ControllerAdvice
public class GlobalModelAttribute {

	private final UserRepository userRepository;

	public GlobalModelAttribute(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@ModelAttribute
	public void addUserNameToModel(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null && auth.isAuthenticated() && !auth.getPrincipal().equals("anonymousUser")) {
			String email = auth.getName();
			User user = userRepository.findByEmail(email).orElse(null);
			if (user != null) {
				model.addAttribute("userName", user.getName());
			}
		}
	}
}
