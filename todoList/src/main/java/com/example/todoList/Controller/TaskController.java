package com.example.todoList.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoList.Entity.Task;
//import com.example.todoList.Service.TaskService;
import com.example.todoList.Service.TaskServiceImpl;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

	@Autowired
	private TaskServiceImpl taskServiceImpl;
	
	@PostMapping("/add") 
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
		Task savedTask = taskServiceImpl.addTask(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/{id}")
	  public ResponseEntity<Optional<Task>> getTaskById(@PathVariable int id) {
	    Optional<Task> taskOptional = taskServiceImpl.getTaskById(id);
	    if (taskOptional.isPresent()) {
	      return ResponseEntity.ok(taskOptional); 
	    } else {
	      return ResponseEntity.notFound().build();
	    }
	  }

	  @GetMapping("/completed")
	  public ResponseEntity<List<Task>> getCompletedTasks() {
	    List<Task> tasks = taskServiceImpl.getCompletedTasks();
	    return ResponseEntity.ok(tasks); 
	  }

	  @GetMapping
	  public ResponseEntity<List<Task>> getAllTasks() {
	    List<Task> tasks = taskServiceImpl.getAllTasks();
	    return ResponseEntity.ok(tasks); 
	  }
	  
	  @PutMapping("/{id}")
	  public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody Task updatedTask) {
	    Optional<Task> updatedTaskOptional = taskServiceImpl.updateTask(id, updatedTask);

	    if (!updatedTaskOptional.isPresent()) {
	      return ResponseEntity.notFound().build(); 
	    }

	    return ResponseEntity.ok(updatedTaskOptional.get()); 
	  }
	
	@DeleteMapping("/{id}")
	  public ResponseEntity<Void> deleteTaskById(@PathVariable int id) {
		taskServiceImpl.deleteTaskById(id);
	    return ResponseEntity.noContent().build(); 
	  }
}
