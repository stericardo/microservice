package com.microservice.api.todo.list.resources;


/**
 * Class for resources {@link PersonResource}.
 *
 * <p>Resources for the endpoints of the Model: Person</p>
 *
 * @author Steven Ricardo Mendez Brenes
 * @version 1.0
 * @since 11/28/2020
 *
 */
public class PersonResource extends BaseResource{

    private long personId;
    private String name;
    private String lastName;
    private String secondLastName;
    private String email;
    private String phone;
    private String personalId;

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

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }
}
