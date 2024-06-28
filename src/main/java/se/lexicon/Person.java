package se.lexicon;

public class Person {
    private int id;
    private String firstName; // Not allowed to be null.
    private String lastName; // Not allowed to be null.
    private String email; // Not allowed to be null.
    private static int sequencer = 0;

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
    public void setEmail(String email) {
        validateStringInput(email, "email");
        this.email = email;
    }

    // Methods
    public String getSummary() {
        return "{id: " + id + ", name: " + getFullName() + ", email: " + email + "}";
    }

}

