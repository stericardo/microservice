package com.microservice.api.todo.list.service.impl;

import com.microservice.api.todo.list.entity.Task;
import com.microservice.api.todo.list.repositories.TaskRepository;
import com.microservice.api.todo.list.resources.TaskResource;
import com.microservice.api.todo.list.service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * Class for services {@link TaskServiceImpl}.
 *
 * <p>Services for the bussiness logic of the Model: Task</p>
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/27/2020
 *
 */
@Service("taskService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TaskServiceImpl extends CrudServiceImpl<Task, Long, TaskResource> implements TaskService {

    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @PostConstruct
    public void initialize() {
        setCrudRepository(taskRepository);
    }
}
