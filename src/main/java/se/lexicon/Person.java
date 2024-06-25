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
        validateAndAssignInput(firstName, "firstName");
        validateAndAssignInput(lastName, "lastName");
        validateAndAssignInput(email, "email");
    }

    // Helper methods
    private void setId() {
        this.id = sequencer + 1;
        sequencer++;
    }

    private void validateAndAssignInput (String input, String paramName) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException(paramName + " must not be null or empty");
        }
        if (paramName.equals("email") && !input.contains("@")) {
            throw new IllegalArgumentException("email must contain @");
        }
        switch (paramName) {
            case "firstName":
                this.firstName = input;
                break;
            case "lastName":
                this.lastName = input;
                break;
            case "email":
                this.email = input;
                break;
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
        validateAndAssignInput(firstName, "firstName");
    }
    public void setLastName(String lastName) {
        validateAndAssignInput(lastName, "lastName");
    }
    public void setEmail(String email) {
        validateAndAssignInput(email, "email");
    }

    // Methods
    public String getSummary() {
        return "{id:" + id + ", name: " + getFullName() + ", email: " + email + "}";
    }

}

