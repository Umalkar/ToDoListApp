package com.example.todoList.Service;

import java.util.List;
import java.util.Optional;

import com.example.todoList.Entity.Task;

public interface TaskService {

	public Task addTask(Task task);
	public Optional<Task> getTaskById(int id) ;
	 public List<Task> getCompletedTasks() ;
	 public List<Task> getAllTasks() ;
	 public void deleteTaskById(int id);
	Optional<Task> updateTask(int id, Task updatedTask);
}
