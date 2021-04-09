package com.microservice.api.todo.list.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * Entity Class for the Person {@link Person}.
 *
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/27/2020
 *
 */
@Entity
@Table(name = "person")
public class Person extends BaseEntity{

    @Column(length = 32)
    private String name;

    @Column(length = 32)
    private String lastName;

    @Column(length = 32)
    private String secondLastName;

    @Column(length = 32)
    private String email;

    @Column(length = 32)
    private String phone;

    @Column(length = 32)
    private String personalId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    @JsonBackReference
    private Set<Task> taskSet;

    @Override
    public boolean onEquals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return 	Objects.equals(name, person.getName())
                && Objects.equals(lastName, person.getLastName())
                && Objects.equals(secondLastName, person.getSecondLastName())
                && Objects.equals(email, person.getEmail())
                && Objects.equals(phone, person.getPhone())
                && Objects.equals(personalId, person.getPersonalId());
    }

    @Override
    public int onHashCode(int result) {
        final int prime = 31;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((secondLastName == null) ? 0 : secondLastName.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        result = prime * result + ((personalId == null) ? 0 : personalId.hashCode());
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Task> getTaskSet() {
        return taskSet;
    }

    public void setTaskSet(Set<Task> taskSet) {
        this.taskSet = taskSet;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }
}
