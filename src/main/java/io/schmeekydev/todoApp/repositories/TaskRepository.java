package io.schmeekydev.todoApp.repositories;

import io.schmeekydev.todoApp.entities.Task;
import io.schmeekydev.todoApp.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract interface TaskRepository extends JpaRepository<Task, Integer> {

	List<Task> findByUser(User user);
	List<Task> findByTitleContaining(String keyword);
}
