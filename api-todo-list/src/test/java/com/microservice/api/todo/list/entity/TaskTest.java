package com.microservice.api.todo.list.entity;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class TaskTest {

    @Test
    @DisplayName("Task Entity Test")
    @Order(1)
    public void taskTest(){
        Map<Task, String> map = new HashMap<>();
        Building building = new Building();
        building.setId(1l);
        Person person = new Person();
        person.setId(1l);
        Task task = new Task();
        task.setId(1l);
        task.setBuilding(building);
        task.setPerson(person);
        task.setDescription("Description");
        task.setName("Task1");
        task.setStatus("Completed");
        map.put(task, "1");
        Task task2 = new Task();
        task2.setId(1l);
        task2.setPerson(person);
        task2.setDescription("Description");
        task2.setName("Task1");
        task2.setStatus("Completed");
        map.put(task2, "1");
        Assert.assertEquals("Expected 2", 2, map.size());
        Assert.assertEquals("Expected true", true,task.equals(task2));
    }

    @Test
    @DisplayName("Task Null Values Entity Test")
    @Order(2)
    public void taskNullTest(){
        Map<Task, String> map = new HashMap<>();
        Task task = new Task();
        task.setId(1l);
        map.put(task, "1");
        task = new Task();
        task.setId(1l);
        map.put(task, "1");
        Assert.assertEquals("Expected 1", 1, map.size());
    }

    @Test
    @DisplayName("Task distinct Id Entity Test")
    @Order(3)
    public void taskIDDiffTest(){
        Map<Task, String> map = new HashMap<>();
        Building building = new Building();
        building.setId(1l);
        Person person = new Person();
        person.setId(1l);
        Task task = new Task();
        task.setId(1l);
        task.setBuilding(building);
        task.setPerson(person);
        task.setDescription("Description");
        task.setName("Task1");
        task.setStatus("Completed");
        map.put(task, "1");
        Task task2 = new Task();
        task2.setId(2l);
        task2.setPerson(person);
        task2.setDescription("Description");
        task2.setName("Task1");
        task2.setStatus("Completed");
        map.put(task2, "1");
        Assert.assertEquals("Expected 2", 2, map.size());
        Assert.assertEquals("Expected false", false,task.equals(task2));
    }

}
