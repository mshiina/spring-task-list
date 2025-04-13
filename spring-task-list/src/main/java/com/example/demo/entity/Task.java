package com.example.demo.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks") //対応するテーブル名
public class Task {
	//フィールド
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //自動採番
	private Integer id; //タスクID
	@Column(name = "user_id") //テーブルのカラム名が異なる場合入れる
	private Integer userId; //usersテーブルのID
	@Column(name = "category_id") //テーブルのカラム名が異なる場合入れる
	private Integer categoryId; //categoriesテーブルのID
	private String title; //タイトル
	private Date closingDate; //期限
	private Integer progress; //進捗状況
	private String memo; //メモ

	//ゲッター
	public Integer getId() {
		return id;
	}

	public Integer getUserId() {
		return userId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public String getTitle() {
		return title;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public Integer getProgress() {
		return progress;
	}

	public String getMdmo() {
		return memo;
	}

}
