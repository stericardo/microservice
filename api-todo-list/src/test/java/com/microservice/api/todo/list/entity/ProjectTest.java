package com.microservice.api.todo.list.entity;


import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class ProjectTest {

    @Test
    @DisplayName("Project Entity Test")
    @Order(1)
    public void projectTest(){
        Map<Project, String> map = new HashMap<>();
        Project project = new Project();
        project.setId(1l);
        project.setTitle("Title");
        project.setDescription("Description project");
        map.put(project, "1");
        Project project2 = new Project();
        project2.setId(1l);
        project2.setTitle("Title");
        map.put(project2, "1");
        Assert.assertEquals("Expected 2", 2, map.size());
        Assert.assertEquals("Expected true", true,project.equals(project2));
    }

    @Test
    @DisplayName("Project Null Values Entity Test")
    @Order(2)
    public void projectNullValuesTest(){
        Map<Project, String> map = new HashMap<>();
        Project project = new Project();
        project.setId(1l);
        map.put(project, "1");
        Project project2 = new Project();
        project2.setId(1l);
        map.put(project2, "1");
        Assert.assertEquals("Expected 1", 1, map.size());
        Assert.assertEquals("Expected false", true,project.equals(project2));
    }

    @Test
    @DisplayName("Project distinct Id Entity Test")
    @Order(3)
    public void projectIDDiffTest(){
        Map<Project, String> map = new HashMap<>();
        Project project = new Project();
        project.setId(1l);
        project.setTitle("Title");
        project.setDescription("Description project");
        map.put(project, "1");
        Project project2 = new Project();
        project2.setId(2l);
        project2.setTitle("Title");
        map.put(project2, "1");
        Assert.assertEquals("Expected 2", 2, map.size());
        Assert.assertEquals("Expected false", false, project.equals(project2));
    }

}