package se.lexicon.Model;

import java.util.Objects;

public class Person {
    private int id;
    private String firstName; // Not allowed to be null.
    private String lastName; // Not allowed to be null.
    private String email; // Not allowed to be null.
    private static int sequencer = 0;
    private AppUser credentials;

    // Constructor
    public Person(String firstName, String lastName, String email) {
        setId();
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }

    // Helper methods
    private void setId() {
        this.id = sequencer + 1;
        sequencer++;
    }

    private void validateStringInput(String input, String paramName) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(paramName + " must not be null or empty");
        }
        if (paramName.equals("email") && !input.contains("@")) {
            throw new IllegalArgumentException("email must contain @");
        }
    }

    // Getters and Setters
    public int getId() {
        return id;
    }
    public String getFullName() {
        return firstName + " " + lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public AppUser getCredentials() {
        return credentials;
    }
    public String getEmail() {
        return email;
    }
    public void setFirstName(String firstName) {
        validateStringInput(firstName, "firstName");
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        validateStringInput(lastName, "lastName");
        this.lastName = lastName;
    }
    public void setCredentials(AppUser credentials) {
        if (credentials == null) {
            throw new IllegalArgumentException("credentials must not be null");
        }
        this.credentials = credentials;
    }
    public void setEmail(String email) {
        validateStringInput(email, "email");
        this.email = email;
    }

    // Methods



    @Override
    public String toString() {
        return "Person{" +
                "id: " + id +
                ", firstName: '" + firstName + '\'' +
                ", lastName: '" + lastName + '\'' +
                ", email: '" + email +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return  Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName) && Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
    }
}

