package com.microservice.api.todo.list.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;

/**
 * Entity Class for the Task {@link Task}.
 *
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/27/2020
 *
 */
@Entity
@Table(name = "task")
public class Task extends BaseEntity {

    @Column(length = 16)
    private String status;

    @Column(length = 32)
    private String name;

    @Column(length = 128)
    private String description;

    @ManyToOne
    @JoinColumn(name = "fk_person", nullable = true)
    @JsonManagedReference
    private Person person;

    @ManyToOne
    @JoinColumn(name = "fk_building", nullable = false)
    @JsonManagedReference
    private Building building;

    @Override
    public int onHashCode(int result) {
        final int prime = 31;
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((person == null) ? 0 : person.hashCode());
        result = prime * result + ((building == null) ? 0 : building.hashCode());
        return result;
    }

    @Override
    public boolean onEquals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return 	Objects.equals(status, task.getStatus())
                && Objects.equals(name, task.getName())
                && Objects.equals(description, task.getDescription())
                && Objects.equals(person, task.getPerson())
                && Objects.equals(building, task.getBuilding());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}
