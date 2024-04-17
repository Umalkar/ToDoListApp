import { Injectable, Optional } from '@angular/core';
import { Task } from '../Model/Task';
import { Observable, catchError, throwError } from 'rxjs'; import { HttpClient, HttpHeaders } from '@angular/common/http';
//import { Optional } from './optional'; // Assuming your optional.ts file path
import { List } from '../Model/list'; 



@Injectable({
  providedIn: 'root'
})
export class TaskServiceService {


  

  private createTaskUrl = 'http://localhost:8080/api/tasks/add';
  private baseUrl = 'http://localhost:8080/api/tasks';
  
  constructor(private http: HttpClient) { }

  
  //List<T> = T[];
  createTask(task: Task): Observable<Task> {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

    // Check for due date before sending request
    if (!task.dueDate) {
      return throwError(() => new Error('Due date is required'));
    }

    return this.http.post<Task>(this.createTaskUrl, task, httpOptions)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: any): Observable<any> {
    console.error('An error occurred:', error);
    return throwError(error);
  }


  getAllTasks(): Observable<List<Task>> {
    return this.http.get<List<Task>>(this.baseUrl);
  }

  getTaskById(id: number): Observable<Task> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<Task>(url);
  }

  getTaskByCompleted(completed: boolean): Observable<Task> {
    const url = `${this.baseUrl}/${completed}`;
    return this.http.get<Task>(url);
  }

  updateTask(task: Task): Observable<void> {
    const url = `${this.baseUrl}/${task.id}`;
    return this.http.put<void>(url, task); // Use PUT for updates
  }

  deleteTaskById(id: number): Observable<void> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.delete<void>(url);
  }

}
