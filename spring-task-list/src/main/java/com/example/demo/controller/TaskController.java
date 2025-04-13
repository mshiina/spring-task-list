package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

	//タスク一覧表示する
	@GetMapping("/tasks")
	public String index(Model model) {
		//tasksテーブルから全タスクの一覧を取得
		List<Task> taskList = taskRepository.findAll();
		//Thymeleafにデータを渡す
		model.addAttribute("tasks", taskList);
		//tasks.htmlを出力
		return "tasks";
	}

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
}
