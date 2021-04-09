package com.microservice.api.todo.list.repositories;

import com.microservice.api.todo.list.entity.Building;
import com.microservice.api.todo.list.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Class for repository {@link BuildingRepository}.
 * <p>
 * Repository for the Model: Building
 */
@Repository("buildingRepository")
public interface BuildingRepository extends JpaRepository<Building, Long>{

    List<Building> findByProject(Project project);

}
