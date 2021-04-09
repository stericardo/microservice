package com.microservice.api.todo.list.repositories;

import com.microservice.api.todo.list.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Class for repository {@link PersonRepository}.
 * <p>
 * Repository for the Model: Person
 */
@Repository("personRepository")
public interface PersonRepository extends JpaRepository<Person, Long>{
}
