import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { TaskServiceService } from '../service/task-service.service';
import { Task } from '../Model/Task';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {
  @Input() tasks: Task[] = []; 
  @Output() taskUpdated = new EventEmitter<Task>(); 

  editingTasks: { [key: number]: boolean } = {};

  editingTaskIndex: number = -1;
  constructor(private taskService: TaskServiceService) { }

    ngOnInit() {
    
    this.getAllTasks;
  }


  getAllTasks() {
    this.taskService.getAllTasks()
      .subscribe(tasks => this.tasks = tasks);
  }

  startEditing(task: Task) {
    this.editingTasks[task.id] = true;
  }

  stopEditing(task: Task) {
    if (this.editingTasks[task.id]) {
      this.updateTask(task);
      delete this.editingTasks[task.id]; 
    }
  }

  updateTask(task: Task) {
    this.taskService.updateTask( task)
      .subscribe(() => { 
        const taskIndex = this.tasks.findIndex(t => t.id === task.id);
        if (taskIndex !== -1) {
          this.tasks[taskIndex] = task; 
          this.taskUpdated.emit(task); 
        }
      }, error => {
        console.error('Error updating task:', error);
        
      });
  }


  

  toggleCompletion(task: Task) {
    task.completed = !task.completed;
    this.updateTask(task); 
  }
  
  
}