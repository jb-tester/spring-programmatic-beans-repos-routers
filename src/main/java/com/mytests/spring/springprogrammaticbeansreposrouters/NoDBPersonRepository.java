package com.mytests.spring.springprogrammaticbeansreposrouters;

import java.util.ArrayList;
import java.util.List;


//@Profile("no-db")
public class NoDBPersonRepository implements PersonRepository {



    List<Person> persons = new ArrayList<>();

    @Override
    public List<Person> findAllPersons() {
        return persons;
    }

    @Override
    public Person findById(int id) {

        Person person = persons.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        return person ;
    }

    @Override
    public List<Person> findByLastName(String arg) {
        return persons.stream().filter(p -> p.getSecondName().equals(arg)).toList();
    }

    @Override
    public Person save(Person person) {
        persons.add(person);
        return person;
    }

    @Override
    public void initDB() {
        persons.add(new Person(1,"ivan", "ivanov", 20));
        persons.add(new Person(2,"petr", "petrov", 30));
        persons.add(new Person(3,"pavel", "pavlov", 40));

    }

}
