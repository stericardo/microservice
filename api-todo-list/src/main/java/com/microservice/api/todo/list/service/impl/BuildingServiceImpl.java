package com.microservice.api.todo.list.service.impl;

import com.microservice.api.todo.list.entity.Building;
import com.microservice.api.todo.list.entity.Project;
import com.microservice.api.todo.list.entity.Task;
import com.microservice.api.todo.list.repositories.BuildingRepository;
import com.microservice.api.todo.list.resources.BuildingResource;
import com.microservice.api.todo.list.service.BuildingService;
import com.microservice.api.todo.list.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Class for services {@link BuildingServiceImpl}.
 *
 * <p>Services for the bussiness logic of the Model: Building</p>
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/27/2020
 *
 */
@Service("buildingService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class BuildingServiceImpl extends CrudServiceImpl<Building, Long, BuildingResource> implements BuildingService {

    private BuildingRepository buildingRepository;
    private ProjectService projectService;

    public BuildingServiceImpl(BuildingRepository buildingRepository, ProjectService projectService){
        this.buildingRepository = buildingRepository;
        this.projectService = projectService;
    }

    @PostConstruct
    public void initialize() {
        setCrudRepository(buildingRepository);
    }

    @Override
    public Building create(Building entity) {
        Optional<Project> projectOptional =projectService.findById(entity.getProject().getId());
        if(projectOptional.isPresent()){
            entity.setProject(projectOptional.get());
            return buildingRepository.save(entity);
        }
        return null;
    }

    @Override
    public List<Building> listBuildingsByProject(long entityProjectId) {
        Optional<Project> projectOptional = projectService.findById(entityProjectId);
        if(projectOptional.isPresent()) {
            return buildingRepository.findByProject(projectOptional.get());
        }
        List<Building> list = new ArrayList<>();
        return list;
    }

    @Override
    public Set<Task> listTasksByBuilding(long entityBuildingId) {
        Optional<Building> buildingOptional = findById(entityBuildingId);
        if(buildingOptional.isPresent()) {
            Set<Task> taskSet = new TreeSet<>();
            buildingOptional.get().getTaskSet()
                    .stream()
                    .forEach( task -> taskSet.add(task));
            return taskSet;
        }
        Set<Task> set = new HashSet<>();
        return set;
    }

}
