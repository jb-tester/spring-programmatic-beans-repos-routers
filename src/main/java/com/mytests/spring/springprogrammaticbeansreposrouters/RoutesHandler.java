package com.mytests.spring.springprogrammaticbeansreposrouters;


import jakarta.servlet.ServletException;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;


public class RoutesHandler {

    private final PersonRepository personRepository;
    public RoutesHandler(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public ServerResponse getAllPersons(ServerRequest request) {
        List<Person> persons = personRepository.findAllPersons();
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(persons);
    }
    public ServerResponse getPersonById(ServerRequest request) {
        int id = Integer.parseInt(request.pathVariable("id"));
        Person person = personRepository.findById(id);
        if (person == null) {
            return ServerResponse.notFound().build();
        }
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(person);
    }
    public ServerResponse getPersonByName(ServerRequest request) {
        String lname = request.pathVariable("lname");
        List<Person> persons = personRepository.findByLastName(lname);
        if (persons == null || persons.isEmpty()) {
            return ServerResponse.notFound().build();
        }
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(persons);
    }

    public ServerResponse createPerson(ServerRequest request) throws ServletException, IOException {
        Person person = request.body(Person.class);
        Person saved = personRepository.save(person);
        return ServerResponse.created(URI.create("/add"))
                .contentType(APPLICATION_JSON)
                .body(saved);
    }

}
