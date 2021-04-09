package com.microservice.api.todo.list.repositories;

import com.microservice.api.todo.list.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class for repository {@link ProjectRepository}.
 * <p>
 * Repository for the Model: Project
 */
@Repository("projectRepository")
public interface ProjectRepository extends JpaRepository<Project, Long>{
}
