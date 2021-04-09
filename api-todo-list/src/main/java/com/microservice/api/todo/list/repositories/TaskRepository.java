package com.microservice.api.todo.list.repositories;

import com.microservice.api.todo.list.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class for repository {@link TaskRepository}.
 * <p>
 * Repository for the Model: Task
 */
@Repository("taskRepository")
public interface TaskRepository extends JpaRepository<Task, Long>{
}
