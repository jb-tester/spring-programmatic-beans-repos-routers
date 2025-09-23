package com.mytests.spring.springprogrammaticbeansreposrouters;


import java.util.List;

public interface PersonRepository {
    List<Person> findAllPersons();

    Person findById(int id);

    List<Person> findByLastName(String arg);

    Person save(Person person);

    void initDB();
}
