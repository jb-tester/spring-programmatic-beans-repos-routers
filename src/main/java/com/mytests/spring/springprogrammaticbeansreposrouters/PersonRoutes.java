package com.mytests.spring.springprogrammaticbeansreposrouters;



import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.servlet.function.RequestPredicates.*;
import static org.springframework.web.servlet.function.RouterFunctions.route;

public class PersonRoutes {

    private final RoutesHandler routesHandler;

    public PersonRoutes(RoutesHandler routesHandler) {
        this.routesHandler = routesHandler;
    }

    public RouterFunction<ServerResponse> routes() {
        return route(GET("/all"), this.routesHandler::getAllPersons)
                .andRoute(POST("/add").and(contentType(APPLICATION_JSON)), this.routesHandler::createPerson)
                .andRoute(GET("/personById/{id}").and(accept(APPLICATION_JSON)), this.routesHandler::getPersonById)
                .andRoute(GET("/personByName/{lname}").and(accept(APPLICATION_JSON)), this.routesHandler::getPersonByName);
    }
}
