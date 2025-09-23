package com.mytests.spring.springprogrammaticbeansreposrouters;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;


class InitDbService {


    private final PersonRepository personRepository;

    InitDbService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @EventListener
    public void on(ApplicationStartedEvent event) {
        System.out.println("============= InitDbService.on =======");
       personRepository.initDB();
    }

}
