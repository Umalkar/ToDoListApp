package com.example.todoList.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todoList.Entity.Task;


@Repository
public interface TaskRepository extends JpaRepository<Task , Integer>{
	
	List<Task> findBycompletionStatus(boolean completionStatus);

	Optional<Task> findById(int id);

}
