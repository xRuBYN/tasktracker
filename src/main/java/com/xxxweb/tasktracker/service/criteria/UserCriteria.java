package com.xxxweb.tasktracker.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.StringFilter;

public class UserCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;
    private StringFilter lastName;
    private StringFilter firstName;
    private StringFilter email;

    public UserCriteria() {}

    public UserCriteria(UserCriteria other) {
        this.lastName = other.lastName == null ? null : other.lastName.copy();
        this.firstName = other.firstName == null ? null : other.firstName.copy();
        this.email = other.email == null ? null : other.email.copy();
    }

    @Override
    public UserCriteria copy() {
        return new UserCriteria(this);
    }

    public StringFilter getLastName() {
        return lastName;
    }

    public void setLastName(StringFilter lastName) {
        this.lastName = lastName;
    }

    public StringFilter getFirstName() {
        return firstName;
    }

    public void setFirstName(StringFilter firstName) {
        this.firstName = firstName;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final UserCriteria that = (UserCriteria) o;
        return (Objects.equals(lastName, that.lastName) && Objects.equals(firstName, that.firstName) && Objects.equals(email, that.email));
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, email);
    }

    @Override
    public String toString() {
        return (
            "UserCriteria{" +
            (lastName != null ? "lastName=" + lastName + ", " : "") +
            (firstName != null ? "firstName=" + firstName + ", " : "") +
            (email != null ? "email=" + email + ", " : "") +
            "}"
        );
    }
}
