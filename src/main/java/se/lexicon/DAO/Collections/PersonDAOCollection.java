package se.lexicon.DAO.Collections;

import se.lexicon.DAO.IdSequencer.PersonIdSequencer;
import se.lexicon.DAO.PersonDAO;
import se.lexicon.Model.Person;

import java.util.ArrayList;

public class PersonDAOCollection implements PersonDAO {
    ArrayList<Person> people;

    // Constructor
    public PersonDAOCollection() {
        people = new ArrayList<>();
    }
    @Override
    public Person persist(Person person) {
        if (person == null) throw new IllegalArgumentException("Person should not be null");
        person.setId(PersonIdSequencer.nextId());
        people.add(person);
        return person;
    }

    @Override
    public Person findById(int personId) {
        if (personId == 0) throw new IllegalArgumentException("PersonId should not be 0");
        for (Person person : people) {
            if (person.getId() == personId) {
                return person;
            }
        }
        return null;
    }

    @Override
    public Person findByEmail(String email) {
        if (email == null || email.trim().isEmpty()) throw new IllegalArgumentException("Email should not be null");
        for (Person person : people) {
            if (person.getEmail().equalsIgnoreCase(email)) {
                return person;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Person> findAll() {
        return people;
    }

    @Override
    public void remove(int personId) {
        if (personId == 0) throw new IllegalArgumentException("PersonId should not be 0");
        for (Person person : people) {
            if (person.getId() == personId) {
                people.remove(person);
                break;
            }
        }
    }
}
