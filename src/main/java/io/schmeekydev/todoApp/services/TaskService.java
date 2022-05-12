package io.schmeekydev.todoApp.services;

import java.util.List;

import io.schmeekydev.todoApp.entities.Task;

public interface TaskService {

    List<Task> getAllTasks();
    Task getTaskById(int taskID);
    List<Task> getTaskByUser(int userID);
    Task createTask(Task task, int userID);
    Task updateTaskByID(Task task, int taskID);
    void deleteTask(int taskID);
    // List<Task> searchTaskTitleByKeyword(String keyword);
}
