package com.microservice.api.todo.list.service;

import com.microservice.api.todo.list.entity.Person;
import com.microservice.api.todo.list.resources.PersonResource;

/**
 * Class for services {@link PersonService}.
 *
 * <p>Services for the bussiness logic of the Model: Person</p>
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/27/2020
 *
 */
public interface PersonService extends CrudService<Person, Long, PersonResource> {
}
