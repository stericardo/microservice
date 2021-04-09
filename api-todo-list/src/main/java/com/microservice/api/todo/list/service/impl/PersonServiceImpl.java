package com.microservice.api.todo.list.service.impl;

import com.microservice.api.todo.list.entity.Person;
import com.microservice.api.todo.list.repositories.PersonRepository;
import com.microservice.api.todo.list.resources.PersonResource;
import com.microservice.api.todo.list.service.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * Class for services {@link PersonServiceImpl}.
 *
 * <p>Services for the bussiness logic of the Model: Person</p>
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/27/2020
 *
 */
@Service("personService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PersonServiceImpl extends CrudServiceImpl<Person, Long, PersonResource> implements PersonService {

    private PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @PostConstruct
    public void initialize() {
        setCrudRepository(personRepository);
    }
}
