package com.microservice.api.todo.list.mappers;

import com.microservice.api.todo.list.entity.Person;
import com.microservice.api.todo.list.resources.PersonResource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class for the mapper {@link PersonMapper}.
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/28/2020
 *
 */
@Component("personMapper")
public class PersonMapper implements BaseMapper<Person, PersonResource>{

    @Override
    public Person convertToEntity(PersonResource r) {
        Person person = new Person();
        return convertToEntity(r, person);
    }

    private Person convertToEntity(PersonResource r, Person person) {
        person.setEmail(r.getEmail());
        person.setLastName(r.getLastName());
        person.setPersonalId(r.getPersonalId());
        person.setSecondLastName(r.getSecondLastName());
        person.setName(r.getName());
        person.setPhone(r.getPhone());
        return person;
    }

    @Override
    public Person convertUpdateToEntity(PersonResource r, Person person) {
        return convertToEntity(r, person);
    }

    @Override
    public Optional<PersonResource> convertToResource(Optional<Person> entity){
        if(entity.isPresent()){
            Person person = entity.get();
            PersonResource personResource = new PersonResource();
            personResource.setEmail(person.getEmail());
            personResource.setLastName(person.getLastName());
            personResource.setPersonalId(person.getPersonalId());
            personResource.setSecondLastName(person.getSecondLastName());
            personResource.setName(person.getName());
            personResource.setPhone(person.getPhone());
            personResource.setPersonId(person.getId());
            return Optional.of(personResource);
        }
        return Optional.empty();
    }

    @Override
    public List<PersonResource> convertToResources(List<Person> entities) {
        List<PersonResource> personResourceList = entities.stream()
                .map(entity -> {
                    PersonResource personResource = new PersonResource();
                    personResource.setEmail(entity.getEmail());
                    personResource.setLastName(entity.getLastName());
                    personResource.setPersonalId(entity.getPersonalId());
                    personResource.setSecondLastName(entity.getSecondLastName());
                    personResource.setName(entity.getName());
                    personResource.setPhone(entity.getPhone());
                    personResource.setPersonId(entity.getId());
                    return personResource;
                })
                .collect(Collectors.toList());
        return personResourceList;
    }

    @Override
    public Set<PersonResource> convertToSetResources(Set<Person> entities) {
        Set<PersonResource> personResourceSet = entities.stream()
                .map(entity -> {
                    PersonResource personResource = new PersonResource();
                    personResource.setEmail(entity.getEmail());
                    personResource.setLastName(entity.getLastName());
                    personResource.setPersonalId(entity.getPersonalId());
                    personResource.setSecondLastName(entity.getSecondLastName());
                    personResource.setName(entity.getName());
                    personResource.setPhone(entity.getPhone());
                    personResource.setPersonId(entity.getId());
                    return personResource;
                })
                .collect(Collectors.toSet());
        return personResourceSet;
    }

}
