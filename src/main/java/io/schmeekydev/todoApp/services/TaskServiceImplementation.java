package io.schmeekydev.todoApp.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.schmeekydev.todoApp.entities.Task;
import io.schmeekydev.todoApp.entities.User;
import io.schmeekydev.todoApp.exceptions.ResourceNotFoundException;
import io.schmeekydev.todoApp.repositories.TaskRepository;
import io.schmeekydev.todoApp.repositories.UserRepository;

@Service
public class TaskServiceImplementation implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    // GET all tasks
	@Override
	public List<Task> getAllTasks() {
        List<Task> tasks = this.taskRepository.findAll();
        if(tasks.isEmpty())
            throw new ResourceNotFoundException("Cannot find any tasks");
        return tasks;
	}

    // GET by id
	@Override
	public Task getTaskById(int taskID) {
        Task task = taskRepository.findById(taskID).orElseThrow(() -> new ResourceNotFoundException("Task", "ID", taskID));
        return task;
	}

    // GET Task by userID
	@Override
	public List<Task> getTaskByUser(int userID) {
        User user = this.userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User", "ID", userID));
        List<Task> tasks = this.taskRepository.findByUser(user);
        return tasks;
	}

    // POST
	@Override
	public Task createTask(Task task, int userID) {
        User user = this.userRepository.findById(userID).orElseThrow(() -> new ResourceNotFoundException("User", "ID", userID));
        task.setUser(user);
        return this.taskRepository.save(task);
	}

    // UPDATE task by task id
	@Override
	public Task updateTaskByID(Task task, int taskID) {
        Task oldTask = this.taskRepository.findById(taskID).orElseThrow(() -> new ResourceNotFoundException("Task", "ID", taskID));
        oldTask.setBody(task.getBody());
        oldTask.setDone(task.isDone());
        oldTask.setTitle(task.getTitle());
        return this.taskRepository.save(oldTask);
	}

    // DELETE
	@Override
	public void deleteTask(int taskID) {
        Task task = this.taskRepository.findById(taskID).orElseThrow(() -> new ResourceNotFoundException("Task", "ID", taskID));
        this.taskRepository.delete(task);
	}

    // Search task from provided keyword
	@Override
	public List<Task> searchTaskTitleByKeyword(String keyword) {
        List<Task> tasks = this.taskRepository.findByTitleContaining(keyword);
        if(tasks.isEmpty())
            throw new ResourceNotFoundException("Task", "Keyword", keyword);
        return tasks;
	}
}
