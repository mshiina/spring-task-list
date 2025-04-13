package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;

@Controller
public class TaskController {
	@Autowired
	TaskRepository taskRepository; //tasksテーブル操作用

	//タスク一覧を表示する
	@GetMapping("/tasks")
	public String index(Model model) {
		//tasksテーブルから全タスクの一覧を取得
		List<Task> taskList = taskRepository.findAll();
		//Thymeleafにデータを渡す
		model.addAttribute("tasks", taskList);
		//tasks.htmlを出力
		return "tasks";
	}
}
