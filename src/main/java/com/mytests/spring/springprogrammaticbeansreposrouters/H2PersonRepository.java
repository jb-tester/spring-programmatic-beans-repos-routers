package com.mytests.spring.springprogrammaticbeansreposrouters;


import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Profile("h2")
public class H2PersonRepository implements PersonRepository {

    @Autowired
    EntityManager entityManager;

    public List<Person> findAllPersons(){
        return entityManager.createQuery("select e from Person e", Person.class).getResultList();
    };

    public List<Person> findByLastName(@Param("lName") String arg){
        return entityManager.createQuery("select e from Person e where e.secondName = :lName", Person.class).setParameter("lName", arg).getResultList();
    };

    @Transactional
    public Person save(Person person) {
        entityManager.persist(person);
        return person;
    }

    public Person findById(int id){
        return entityManager.createQuery("select e from Person e where e.id = :id", Person.class).setParameter("id", id).getSingleResult();
    }

    @Transactional
    public void initDB() {
        entityManager.persist(new Person(1,"ivan", "ivanov", 20));
        entityManager.persist(new Person(2,"petr", "petrov", 30));
        entityManager.persist(new Person(3,"pavel", "pavlov", 40));
    }
}