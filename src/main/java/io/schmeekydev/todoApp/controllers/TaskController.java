package io.schmeekydev.todoApp.controllers;

import java.util.List;

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

import io.schmeekydev.todoApp.entities.Task;
import io.schmeekydev.todoApp.payloads.ApiResponse;
import io.schmeekydev.todoApp.services.TaskService;

@RestController
@RequestMapping(path = "/api/v1/task")
public class TaskController {

	@Autowired
	private TaskService taskService;

    // GET all tasks
    @GetMapping()
    public ResponseEntity<List<Task>> getAllTasks(){
        return new ResponseEntity<>(this.taskService.getAllTasks(), HttpStatus.OK);
    }

    // GET Task by userID
	@GetMapping("/user/{userID}")
	public ResponseEntity<List<Task>> getTaskByUser(
		@PathVariable("userID") int userID
	) {
		return new ResponseEntity<>(
			this.taskService.getTaskByUser(userID),
			HttpStatus.OK
		);
	}

    // CREATE task
	@PostMapping("/user/{userID}")
	public ResponseEntity<Task> createTask(
		@RequestBody Task task,
		@PathVariable("userID") int userID
	) {
		Task newTask = taskService.createTask(task, userID);
		return new ResponseEntity<>(newTask, HttpStatus.CREATED);
	}

    // UPDATE task by task id
    @PutMapping("/{taskID}")
    public ResponseEntity<Task> updateTaskByID(
        @RequestBody Task newTask,
        @PathVariable("taskID") int taskID
    ) {
        return new ResponseEntity<>(this.taskService.updateTaskByID(newTask, taskID), HttpStatus.OK);
    }

    // DELETE Task By Id
    @DeleteMapping("/{taskID}")
    public ResponseEntity<ApiResponse> deleteTask(
        @PathVariable("taskID") int taskID
    ) {
        this.taskService.deleteTask(taskID);
        return new ResponseEntity<>(new ApiResponse(String.format("Deleted Task with id %s", taskID), true), HttpStatus.OK);
    }

    // search from keyword
    // @GetMapping("/search/{keyword}")
    // public ResponseEntity<List<Task>> searchTaskTitleByKeyword(@PathVariable("keyword") String keyword){
    //     List<Task> tasks = this.taskService.searchTaskTitleByKeyword(keyword);
    //     return new ResponseEntity<>(tasks, HttpStatus.OK);
    // }
}
