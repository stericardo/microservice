package com.microservice.api.todo.list.service;

import com.microservice.api.todo.list.entity.Project;
import com.microservice.api.todo.list.resources.ProjectResource;

/**
 * Class for services {@link ProjectService}.
 *
 * <p>Services for the bussiness logic of the Model: Project</p>
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/27/2020
 *
 */
public interface ProjectService extends CrudService<Project, Long, ProjectResource> {
}
