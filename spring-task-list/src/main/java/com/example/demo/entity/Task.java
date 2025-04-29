package com.example.demo.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	private Integer progress; // 進捗状況（0: 未着手, 1: 進行中, 2: 完了）
	private String memo; //メモ

	//コンストラクタ(新規タスク登録)
	public Task() {
	}

	//全て初期化可能なコンストラクタ（更新処理用）
	public Task(Integer id, Integer userId, Integer categoryId, String title, Date closingDate, Integer progress,
			String memo) {
		this.id = id;
		this.userId = userId;
		this.categoryId = categoryId;
		this.title = title;
		this.closingDate = closingDate;
		this.progress = progress;
		this.memo = memo;
	}

	public Task(Integer categoryId, String title, Date closingDate, Integer progress,
			String memo) {
		this.categoryId = categoryId;
		this.title = title;
		this.closingDate = closingDate;
		this.progress = progress;
		this.memo = memo;
	}

	//カテゴリ名を取得
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", insertable = false, updatable = false)
	private Category category;// カテゴリー情報を関連付け

	// 進捗ラベルを取得するためのメソッド
	public String getProgressLabel() {
		return ProgressStatus.fromCode(this.progress).getLabel();
	}

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

	public String getMemo() {
		return memo;
	}

	// カテゴリのgetter/setter メソッド
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
