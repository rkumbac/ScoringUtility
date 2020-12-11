package org.abc.domain.model;

import java.util.Optional;

public class Name {
    String firstName;
    String lastName;

    public Name() {
        firstName = null;
        lastName = null;
    }

    public Name(String firstName) {
        this.firstName = firstName;
    }

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return Optional.ofNullable(firstName).orElse("");
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return Optional.ofNullable(lastName).orElse("");
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return getFirstName() + getLastName();
    }

    @Override
    public String toString() {
        return this.getFirstName() + this.getLastName();
    }
}
