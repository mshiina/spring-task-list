package com.example.demo.util;

import jakarta.annotation.PostConstruct;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordHashGenerator {

	private final PasswordEncoder passwordEncoder;

	public PasswordHashGenerator(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@PostConstruct
	public void generateHash() {
		// 複数のパスワードを配列やリストで指定
		String[] rawPasswords = { "test123", "password456", "secure789" };

		// 各パスワードをハッシュ化して出力
		for (String rawPassword : rawPasswords) {
			String hashedPassword = passwordEncoder.encode(rawPassword);
			System.out.println("Raw password: " + rawPassword + " => BCrypt hash: " + hashedPassword);
		}
	}
}
