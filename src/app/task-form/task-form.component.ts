import { Component, OnInit } from '@angular/core';
import { Task } from '../Model/Task';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TaskServiceService } from '../service/task-service.service';

@Component({
  selector: 'app-task-form',
  templateUrl: './task-form.component.html',
  styleUrls: ['./task-form.component.css']
})
export class TaskFormComponent implements OnInit{

 

taskForm: FormGroup;

constructor(private taskService: TaskServiceService, private fb: FormBuilder) { 
  
    this.taskForm = this.fb.group({ 
      title: ['', Validators.required],
      description: ['', Validators.required],
      dueDate: [new Date(), Validators.required],
      completed: [false] 
    });
  
}

ngOnInit() {
}

onSubmit() {
  if (this.taskForm.valid) {
    const task: Task = {
      ...this.taskForm.value 
    };
    this.taskService.createTask(task)
      .subscribe(createdTask => {
        console.log('Task created successfully!', createdTask);
        this.taskForm.reset(); 
      },
      error => {
        console.error('Error creating task:', error.message);
      });
  }
}
}