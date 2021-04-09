package com.microservice.api.todo.list.mappers;

import com.microservice.api.todo.list.config.BaseIntegrationTestAdapter;
import com.microservice.api.todo.list.entity.Person;
import com.microservice.api.todo.list.resources.PersonResource;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PersonMapperTest extends BaseIntegrationTestAdapter {

    @Autowired
    PersonMapper personMapper;

    @Test
    @DisplayName("convertToSetResources Task - Mapper")
    @Order(1)
    public void convertToSetResourcesTest(){
        Set<Person> entities = new HashSet<>();
        entities.add(createPerson());
        Set<PersonResource> personResourceSet =
                personMapper.convertToSetResources(entities);
        Assert.assertEquals("Expected 1",1, personResourceSet.size());
    }

    @Test
    @DisplayName("convertToResources Task - Mapper")
    @Order(2)
    public void convertToResourcesTest(){
        List<Person> entities = new ArrayList<>();
        entities.add(createPerson());
        List<PersonResource> personResourceSet =
                personMapper.convertToResources(entities);
        Assert.assertEquals("Expected 1",1, personResourceSet.size());
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
