package com.microservice.api.todo.list.service.impl;

import com.microservice.api.todo.list.entity.Project;
import com.microservice.api.todo.list.repositories.ProjectRepository;
import com.microservice.api.todo.list.resources.ProjectResource;
import com.microservice.api.todo.list.service.ProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * Class for services {@link ProjectServiceImpl}.
 *
 * <p>Services for the bussiness logic of the Model: Project</p>
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/27/2020
 *
 */
@Service("projectService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProjectServiceImpl extends CrudServiceImpl<Project, Long, ProjectResource> implements ProjectService {

    private ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository){
        this.projectRepository = projectRepository;
    }

    @PostConstruct
    public void initialize() {
        setCrudRepository(projectRepository);
    }
}
