package com.microservice.api.todo.list.mappers;

import com.microservice.api.todo.list.entity.Building;
import com.microservice.api.todo.list.entity.Person;
import com.microservice.api.todo.list.entity.Task;
import com.microservice.api.todo.list.resources.TaskResource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Class for the mapper {@link TaskMapper}.
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/28/2020
 *
 */
@Component("taskMapper")
public class TaskMapper implements BaseMapper<Task, TaskResource>{

    @Override
    public Task convertToEntity(TaskResource r) {
        Task task = new Task();
        return convertToEntity(r, task);
    }

    public Task convertToEntity(TaskResource r, Task task) {
        Building building = new Building();
        building.setId(r.getBuildingId());
        task.setBuilding(building);
        long personId = r.getPersonId();
        if(personId != 0) {
            Person person = new Person();
            person.setId(r.getPersonId());
            task.setPerson(person);
        } else {
            task.setPerson(null);
        }
        task.setDescription(r.getDescription());
        task.setName(r.getName());
        task.setStatus(r.getStatus());
        return task;
    }

    @Override
    public Task convertUpdateToEntity(TaskResource r, Task task) {
        return convertToEntity(r, task);
    }

    @Override
    public Optional<TaskResource> convertToResource(Optional<Task> entity){
        if(entity.isPresent()){
            Task task = entity.get();
            TaskResource taskResource = new TaskResource();
            if(task.getPerson() != null) {
                taskResource.setPersonName(task.getPerson().getName() + " "
                        + task.getPerson().getLastName());
                taskResource.setPersonId(task.getPerson().getId());
            }
            taskResource.setTaskId(task.getId());
            taskResource.setDescription(task.getDescription());
            taskResource.setName(task.getName());
            taskResource.setStatus(task.getStatus());
            if(task.getBuilding() != null) {
                taskResource.setBuildingId(task.getBuilding().getId());
                taskResource.setBuildingName(task.getBuilding().getName());
            }
            return Optional.of(taskResource);
        }
        return Optional.empty();
    }

    @Override
    public List<TaskResource> convertToResources(List<Task> entities) {
        List<TaskResource> taskResourceList = entities.stream()
                .map(entity -> {
                    TaskResource taskResource = new TaskResource();
                    if(entity.getPerson() != null) {
                        taskResource.setPersonName(entity.getPerson().getName() + " "
                                + entity.getPerson().getLastName());
                        taskResource.setPersonId(entity.getPerson().getId());
                    }
                    taskResource.setDescription(entity.getDescription());
                    taskResource.setName(entity.getName());
                    taskResource.setStatus(entity.getStatus());
                    if(entity.getBuilding() != null) {
                        taskResource.setBuildingId(entity.getBuilding().getId());
                        taskResource.setBuildingName(entity.getBuilding().getName());
                    }
                    taskResource.setTaskId(entity.getId());
                    return taskResource;
                })
                .collect(Collectors.toList());
        return taskResourceList;
    }

    @Override
    public Set<TaskResource> convertToSetResources(Set<Task> entities) {
        Set<TaskResource> taskResourceSet = entities.stream()
                .map(entity -> {
                    TaskResource taskResource = new TaskResource();
                    if(entity.getPerson() != null) {
                        taskResource.setPersonName(entity.getPerson().getName() + " "
                                + entity.getPerson().getLastName());
                        taskResource.setPersonId(entity.getPerson().getId());
                    }
                    taskResource.setDescription(entity.getDescription());
                    taskResource.setName(entity.getName());
                    taskResource.setStatus(entity.getStatus());
                    if(entity.getBuilding() != null) {
                        taskResource.setBuildingId(entity.getBuilding().getId());
                        taskResource.setBuildingName(entity.getBuilding().getName());
                    }
                    taskResource.setTaskId(entity.getId());
                    return taskResource;
                })
                .collect(Collectors.toCollection( () -> new TreeSet<>(TaskMapper::compareSet)));
        return taskResourceSet;
    }

    private static int getStatusInt(TaskResource taskResource){
        return taskResource.getStatus().equals("Todo") ? 1 :
                taskResource.getStatus().equals("In Progress") ? 2: 3;
    }

    public static int compareSet(Object o1, Object o2){
        TaskResource t1 = (TaskResource)o1;
        TaskResource t2 = (TaskResource)o2;
        int statusT1 = getStatusInt(t1);
        int statusT2 = getStatusInt(t2);
        if(statusT1 == statusT2) {
            return compareNameSet(t1, t2);
        }

        if(statusT1 == 1){
            return -1;
        }else if(statusT2 == 1){
            return 1;
        }

        if(statusT1 == 2){
            return -1;
        }else if(statusT2 == 2){
            return 1;
        }

        if(statusT1 == 3){
            return -1;
        }else {
            return 1;
        }
    }

    public static int compareNameSet(TaskResource o1, TaskResource o2){
        int result = o1.getName().compareTo(o2.getName());
        if(result == 0){
            return -1;
        }
        return result;
    }
}
