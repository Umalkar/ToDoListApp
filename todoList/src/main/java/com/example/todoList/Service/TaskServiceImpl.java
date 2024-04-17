package com.example.todoList.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todoList.Entity.Task;
import com.example.todoList.Repository.TaskRepository;


@Service
public class TaskServiceImpl implements TaskService{

	@Autowired
	private TaskRepository taskRepository;

	public Task addTask(Task task) {
		
		if(task.getDueDate() == null)
		// TODO Auto-generated method stub
			
			throw new IllegalArgumentException("Due date is required");
		
		
		return taskRepository.save(task);
	}

	
	@Override
	public Optional<Task> getTaskById(int id) {
	    return taskRepository.findById(id);
	  }

	  public List<Task> getCompletedTasks() {
	    return taskRepository.findBycompletionStatus(true);
	  }

	  public List<Task> getAllTasks() {
		    return taskRepository.findAll();
		  }

	  @Override
	public Optional<Task> updateTask(int id, Task updatedTask) {
	    Optional<Task> existingTaskOptional = taskRepository.findById(id);
	    if (!existingTaskOptional.isPresent()) {
	      return Optional.empty();
	    }
	    Task existingTask = existingTaskOptional.get();
	    existingTask.setTitle(updatedTask.getTitle());
	    existingTask.setDescription(updatedTask.getDescription());
	    existingTask.setDueDate(updatedTask.getDueDate());
	    existingTask.setCompletionStatus(updatedTask.isCompletionStatus());
	    return Optional.of(taskRepository.save(existingTask));
	  }
	
	
	public void deleteTaskById(int id) {
	    taskRepository.deleteById(id);
	  }

	}
