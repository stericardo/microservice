package com.microservice.api.todo.list.controller;

import com.microservice.api.todo.list.entity.Person;
import com.microservice.api.todo.list.mappers.PersonMapper;
import com.microservice.api.todo.list.resources.PersonResource;
import com.microservice.api.todo.list.service.PersonService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * Class for the controller {@link PersonController}.
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/27/2020
 *
 */
@RestController
@RequestMapping("person")
@OpenAPIDefinition(info = @Info(
        title = "OpenAPIDefinition PersonController",
        version = "1",
        description = "PersonController"))
public class PersonController extends AbstractBasicCrudController <Person, Long, PersonResource> {

    private PersonService personService;
    private PersonMapper personMapper;

    public PersonController(PersonService personService, PersonMapper personMapper){
        this.personService = personService;
        this.personMapper = personMapper;
    }

    @PostConstruct
    public void initialize() {
        setService(personService);
        setBaseMapper(personMapper);
    }


}



