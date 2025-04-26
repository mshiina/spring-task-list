package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/users/new")
	public String register(
			@RequestParam String name,
			@RequestParam String email,
			@RequestParam String password) {
		if (userRepository.findByEmail(email).isPresent()) {
			return "redirect:/users/new?error";//重複チェック
		}

		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setPassword(passwordEncoder.encode(password));
		userRepository.save(user);
		return "redirect:/login"; // ログインページにリダイレクト
	}

	@GetMapping("/users/new")
	public String registerForm() {
		return "users/new";
	}

	@GetMapping("/login")
	public String loginPage() {
		return "login"; // ログインしていようがしていまいが、普通にページを返す
	}
}
