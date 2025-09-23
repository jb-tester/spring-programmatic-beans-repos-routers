package com.mytests.spring.springprogrammaticbeansreposrouters;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("no-db")
public class NoDbRepositoryTest {


    @Autowired
    private PersonRepository personRepository;

    @Test
    void testPostRepository() {
        assertThat(this.personRepository).isInstanceOf(NoDBPersonRepository.class);
    }

    @Test
    void testGetAll() {
        List<Person> allPersons = personRepository.findAllPersons();
        assertNotNull(allPersons);
        assertNotEquals(0, allPersons.size());
    }

    @Test
    void testAddPerson() {
        Person newPerson = new Person(20, "John", "Doe", 30);
        personRepository.save(newPerson);
        List<Person> allPersons = personRepository.findAllPersons();
        assertEquals(4, allPersons.size());
    }

    @Test
    void testFindById() {
        Person person = personRepository.findById(1);
        assertNotNull(person);
        assertEquals("ivan", person.getFirstName());
    }

}
