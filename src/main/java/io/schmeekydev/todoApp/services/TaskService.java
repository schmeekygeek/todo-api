package io.schmeekydev.todoApp.services;

import java.util.List;

import io.schmeekydev.todoApp.entities.Task;

public interface TaskService {

    public List<Task> getAllTasks();
    public Task getTaskById(int taskID);
    public Task createTask(Task task, int userID);
    public Task updateTask(Task task, int taskID);
    public void deleteTask(int taskID);
}
