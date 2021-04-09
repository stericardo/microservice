package com.microservice.api.todo.list.service;

import com.microservice.api.todo.list.entity.Task;
import com.microservice.api.todo.list.resources.TaskResource;

/**
 * Class for services {@link TaskService}.
 *
 * <p>Services for the bussiness logic of the Model: Task</p>
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/27/2020
 *
 */
public interface TaskService extends CrudService<Task, Long, TaskResource> {
}
