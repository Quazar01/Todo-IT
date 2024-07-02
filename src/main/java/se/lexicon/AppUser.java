package se.lexicon;

import java.util.Objects;

public class AppUser {
    private String username; // Not allowed to be null or empty.
    private String password; // Not allowed to be null or empty.
    private AppRole role; // Not allowed to be null.

    // Constructor
    public AppUser(String username, String password, AppRole role) {
        setUsername(username);
        setPassword(password);
        setRole(role);
    }

    // Helper methods
    private void setRole(AppRole role) {
        if (role == null) {
            throw new IllegalArgumentException("role must not be null");
        }
        this.role = role;
    }
    // Getters and Setters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("username must not be null or empty");
        }
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("password must not be null or empty");
        }
        this.password = password;
    }
    public AppRole getRole() {
        return role;
    }


    @Override
    public String toString() {
        return "AppUser{" +
                "username: '" + username + '\'' +
                ", role: " + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(username, appUser.username) && role == appUser.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role);
    }

}
