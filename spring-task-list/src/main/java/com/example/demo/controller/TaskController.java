package com.example.demo.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.entity.Task;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.TaskRepository;

@Controller
public class TaskController {
	@Autowired
	TaskRepository taskRepository; //tasksテーブル操作用

	@Autowired
	CategoryRepository categoryRepository; //categoriesテーブル操作用

	//タスク一覧表示（カテゴリーによる絞り込み）
	@GetMapping("/tasks")
	public String tasks(
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			Model model) {

		//categoriesテーブルから全カテゴリー一覧を表示
		List<Category> categoryList = categoryRepository.findAll();
		model.addAttribute("categories", categoryList);

		//商品一覧情報の取得
		List<Task> taskList = null;
		if (categoryId == null) {
			taskList = taskRepository.findAll();
		} else {
			//tasksテーブルをカテゴリーIDを指定して一覧を取得
			taskList = taskRepository.findByCategoryId(categoryId);
		}
		model.addAttribute("tasks", taskList);
		return "tasks";
	}

	//新規登録画面の表示
	@GetMapping("/tasks/add")
	public String create() {
		return "addTask";
	}

	//新規登録処理
	@PostMapping("/tasks/add")
	public String task(
			@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(value = "title", defaultValue = "") String title,
			@RequestParam(value = "closingDate", defaultValue = "") Date closingDate,
			@RequestParam(value = "progress", defaultValue = "") Integer progress,
			@RequestParam(value = "memo", defaultValue = "") String memo,
			Model model) {
		//Taskオブジェクトの作成
		Task task = new Task(categoryId, title, closingDate, progress, memo);
		//tasksテーブルへの反映(INSERT)
		taskRepository.save(task);
		//「/tasks」にGETでリクエストし直す（リダイレクト）
		return "redirect:/tasks";
	}

	//更新画面表示
	@GetMapping("/tasks/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Model model) {
		//tasksテーブルをID（主キー）で検索
		Task task = taskRepository.findById(id).get();
		model.addAttribute("task", task);
		return "editTask";
	}

	//更新処理
	@PostMapping("/tasks/{id}/edit")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam(value = "userId", defaultValue = "") Integer userId,
			@RequestParam(value = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(value = "title", defaultValue = "") String title,
			@RequestParam(value = "closingDate", defaultValue = "") Date closingDate,
			@RequestParam(value = "progress", defaultValue = "") Integer progress,
			@RequestParam(value = "memo", defaultValue = "") String memo,
			Model model) {
		//Taskオブジェクトの生成
		Task task = new Task(id, userId, categoryId, title, closingDate, progress, memo);
		//tasksテーブルへの反映(UPDATE)
		taskRepository.save(task);
		//「/tasks」にGETでリクエストし直す(リダイレクト)
		return "redirect:/tasks";
	}

}
