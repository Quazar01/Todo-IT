package se.lexicon.DAO;

import se.lexicon.Model.Person;

import java.util.ArrayList;

public interface PersonDAO {
    Person persist(Person person);
    Person findById(int personId);
    Person findByEmail(String email);
    ArrayList<Person> findAll();
    void remove(int personId);
}
