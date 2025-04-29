package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users") //対応するテーブル名
public class User {
	//フィールド
	@Id //主キー
	@GeneratedValue(strategy = GenerationType.IDENTITY) //自動採番
	private Integer id;

	@Column(unique = true) // ユーザー名は重複禁止
	private String email;
	private String name;
	private String password;

	public User() {
	}

	public User(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
	}

	//セッター
	public void setEmail(String email) {
		this.email = email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//ゲッター
	public Integer getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}
}
