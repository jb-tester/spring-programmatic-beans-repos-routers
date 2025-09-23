package com.mytests.spring.springprogrammaticbeansreposrouters;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;


class InitDbService {


    private final PersonRepository personRepository;

    InitDbService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @EventListener
    public void initDbOnApplicationStartedEvent(ApplicationStartedEvent event) {
        System.out.println("============= database initialization.... =======");
       personRepository.initDB();
    }

}
