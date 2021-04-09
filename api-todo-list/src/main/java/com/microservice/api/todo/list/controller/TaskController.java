package com.microservice.api.todo.list.controller;

import com.microservice.api.todo.list.entity.Task;
import com.microservice.api.todo.list.mappers.TaskMapper;
import com.microservice.api.todo.list.resources.TaskResource;
import com.microservice.api.todo.list.service.TaskService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * Class for the controller {@link TaskController}.
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/27/2020
 *
 */
@RestController
@RequestMapping("task")
@OpenAPIDefinition(info = @Info(
        title = "OpenAPIDefinition PersonController",
        version = "1",
        description = "PersonController"))
public class TaskController extends AbstractBasicCrudController <Task, Long, TaskResource> {

    private TaskService taskService;
    private TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper){
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @PostConstruct
    public void initialize() {
        setService(taskService);
        setBaseMapper(taskMapper);
    }


}



