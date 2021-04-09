package com.microservice.api.todo.list.controller;

import com.microservice.api.todo.list.entity.Project;
import com.microservice.api.todo.list.mappers.ProjectMapper;
import com.microservice.api.todo.list.resources.ProjectResource;
import com.microservice.api.todo.list.service.ProjectService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * Class for the controller {@link ProjectController}.
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/27/2020
 *
 */
@RestController
@RequestMapping("project")
@OpenAPIDefinition(info = @Info(
        title = "OpenAPIDefinition ProjectController",
        version = "1",
        description = "ProjectController"))
public class ProjectController extends AbstractBasicCrudController <Project, Long, ProjectResource> {

    private ProjectService projectService;
    private ProjectMapper projectMapper;

    public ProjectController(ProjectService projectService, ProjectMapper projectMapper){
        this.projectService = projectService;
        this.projectMapper = projectMapper;
    }

    @PostConstruct
    public void initialize() {
        setService(projectService);
        setBaseMapper(projectMapper);
    }


}



