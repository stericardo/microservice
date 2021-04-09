package com.microservice.api.todo.list.mappers;

import com.microservice.api.todo.list.config.BaseIntegrationTestAdapter;
import com.microservice.api.todo.list.entity.Building;
import com.microservice.api.todo.list.entity.Person;
import com.microservice.api.todo.list.entity.Task;
import com.microservice.api.todo.list.resources.TaskResource;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class TaskMapperTest extends BaseIntegrationTestAdapter {

    @Autowired
    private TaskMapper taskMapper;

    @Test
    @DisplayName("convertToResources Task - Mapper")
    @Order(1)
    public void convertToResourcesTest(){
        List<Task> entities = new ArrayList<>();
        Task task = new Task();
        Map<Task, String> map = new HashMap<>();
        task.setId(1l);
        task.setBuilding(createBuilding());
        task.setPerson(createPerson());
        task.setDescription("Description");
        task.setName("Task1");
        task.setStatus("Completed");
        entities.add(task);
        List<TaskResource> taskResourceList = taskMapper.convertToResources(entities);
        Assert.assertEquals("Expected 1",1, taskResourceList.size());
    }

    @Test
    @DisplayName("convertToSetResources Task - Mapper")
    @Order(2)
    public void convertToSetResourcesTest(){
        Set<Task> entities = new HashSet<>();
        Task task = new Task();
        task.setId(1l);
        task.setBuilding(createBuilding());
        task.setPerson(createPerson());
        task.setDescription("Description");
        task.setName("Task1");
        task.setStatus("Completed");
        entities.add(task);
        Set<TaskResource> taskResourceList = taskMapper.convertToSetResources(entities);
        Assert.assertEquals("Expected 1",1, taskResourceList.size());
    }

    private Building createBuilding(){
        Building building = new Building();
        building.setId(1l);
        building.setName("Building Name");
        return building;
    }

    private Person createPerson(){
        Person person = new Person();
        person.setId(1l);
        person.setName("name");
        person.setLastName("lastName");
        person.setSecondLastName("secondLastName");
        person.setPhone("123");
        person.setEmail("person@equitativa.com");
        person.setPersonalId("1245");
        return person;
    }

}
