package io.schmeekydev.todoApp.repositories;

import io.schmeekydev.todoApp.entities.Task;
import io.schmeekydev.todoApp.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	List<Task> findByUser(User user);

	// @Query(value = "SELECT t FROM task WHERE t.title like '%?1%'")
	// List<Task> searchTaskTitleByKeyword(String keyword);
}
