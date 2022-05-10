package io.schmeekydev.todoApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.schmeekydev.todoApp.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{}
