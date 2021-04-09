package com.microservice.api.todo.list.entity;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class PersonTest {

    @Test
    @DisplayName("Person Entity Test")
    @Order(1)
    public void personTest(){
        Map<Person, String> map = new HashMap<>();
        Person person = new Person();
        person.setId(1l);
        person.setName("name");
        person.setLastName("lastName");
        person.setSecondLastName("secondLastName");
        person.setPhone("123");
        person.setEmail("person@equitativa.com");
        person.setPersonalId("1245");
        map.put(person, "1");
        Person person2 = new Person();
        person2.setName("name");
        person2.setId(1l);
        person2.setLastName("lastName");
        person2.setSecondLastName("secondLastName");
        person2.setPhone("123");
        person2.setEmail("person@equitativa.com");
        person2.setPersonalId("12456");
        map.put(person2, "1");
        Assert.assertEquals("Expected 2", 2, map.size());
        Assert.assertEquals("Expected true", true,person.equals(person2));
    }

    @Test
    @DisplayName("Person Null Values Entity Test")
    @Order(2)
    public void personNullTest(){
        Map<Person, String> map = new HashMap<>();
        Person person = new Person();
        person.setId(1l);
        map.put(person, "1");
        Person person2 = new Person();
        person2.setId(1l);
        map.put(person2, "1");
        Assert.assertEquals("Expected 1", 1, map.size());
        Assert.assertEquals("Expected true", true, person.equals(person2));
    }

    @Test
    @DisplayName("Person Distinct Id Entity Test")
    @Order(3)
    public void personIdDiffTest(){
        Map<Person, String> map = new HashMap<>();
        Person person = new Person();
        person.setId(1l);
        person.setName("name");
        person.setLastName("lastName");
        person.setSecondLastName("secondLastName");
        person.setPhone("123");
        person.setEmail("person@equitativa.com");
        person.setPersonalId("1245");
        map.put(person, "1");
        Person person2 = new Person();
        person2.setName("name");
        person2.setId(1l);
        person2.setLastName("lastName");
        person2.setSecondLastName("secondLastName");
        person2.setPhone("123");
        person2.setEmail("person@equitativa.com");
        person2.setPersonalId("1245");
        map.put(person2, "1");
        Assert.assertEquals("Expected 1", 1, map.size());
        Assert.assertEquals("Expected true", true, person.equals(person2));
    }

}
