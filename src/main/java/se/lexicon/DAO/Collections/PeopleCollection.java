package se.lexicon.DAO.Collections;

import se.lexicon.DAO.People;
import se.lexicon.Model.Person;
import se.lexicon.db.SQLConnection;

import java.sql.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PeopleCollection implements People {
    ArrayList<Person> people;

    @Override
    public Person create(Person person) {
        try {
            Connection connection = SQLConnection.getConnection();
            String CREATE_PERSON = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PERSON, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, person.getFirst_name());
            preparedStatement.setString(2, person.getLast_name());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                person.setPerson_id(resultSet.getInt(1));
            }
            return person;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Person> findAll() {
        List<Person> result = new ArrayList<>();
        try {
            Connection connection = SQLConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM person");

            while (resultSet.next()) {
                Person person = new Person(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")
                );
                result.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Person findById(int id) {
        try {
            Connection connection = SQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE person_id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Person(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Person> findByName(String name) {
        // Seperate the first name and last name
        String firstName = name.split(" ")[0];
        String lastName = name.split(" ")[1];
        try {
            Connection connection = SQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM person WHERE first_name = ? AND last_name = ?");
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Person> result = new ArrayList<>();
            while (resultSet.next()) {
                Person person = new Person(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")
                );
                result.add(person);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Person Update(Person person) {
        try {
            Connection connection = SQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE person SET first_name = ?, last_name = ? WHERE person_id = ?");
            preparedStatement.setString(1, person.getFirst_name());
            preparedStatement.setString(2, person.getLast_name());
            preparedStatement.setInt(3, person.getPerson_id());

            preparedStatement.execute();
            return person;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        try {
            Connection connection = SQLConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM person WHERE person_id = ?");
            preparedStatement.setInt(1, id);

            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
