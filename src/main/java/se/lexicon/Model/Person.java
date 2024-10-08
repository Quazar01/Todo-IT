package se.lexicon.Model;

import java.util.Objects;

public class Person {
    private int person_id;
    private String first_name; // Not allowed to be null.
    private String last_name; // Not allowed to be null.
    private String email; // Not allowed to be null.
    private static int sequencer = 0;
    private AppUser credentials;

    // Constructor

    public Person(String first_name, String last_name) {
        setFirst_name(first_name);
        setLast_name(last_name);
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
    public int getPerson_id() {
        return person_id;
    }
    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }
    public String getFullName() {
        return first_name + " " + last_name;
    }
    public String getFirst_name() {
        return first_name;
    }
    public String getLast_name() {
        return last_name;
    }
    public AppUser getCredentials() {
        return credentials;
    }
    public String getEmail() {
        return email;
    }
    public void setFirst_name(String first_name) {
        validateStringInput(first_name, "firstName");
        this.first_name = first_name;
    }
    public void setLast_name(String last_name) {
        validateStringInput(last_name, "lastName");
        this.last_name = last_name;
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
                "id: " + person_id +
                ", firstName: '" + first_name + '\'' +
                ", lastName: '" + last_name + '\'' +
                ", email: '" + email +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return  Objects.equals(first_name, person.first_name) && Objects.equals(last_name, person.last_name) && Objects.equals(email, person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first_name, last_name, email);
    }
}

