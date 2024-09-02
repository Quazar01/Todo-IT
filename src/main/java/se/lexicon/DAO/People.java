package se.lexicon.DAO;

import se.lexicon.Model.Person;

import java.util.ArrayList;
import java.util.Collection;

public interface People {
//    Person persist(Person person);
//    Person findById(int personId);
//    Person findByEmail(String email);
//    ArrayList<Person> findAll();
//    void remove(int personId);

    Person create(Person person);
    Collection<Person> findAll();
    Person findById(int id);
    Collection<Person> findByName(String name);
    Person Update(Person person);
    boolean delete(int id);
}
