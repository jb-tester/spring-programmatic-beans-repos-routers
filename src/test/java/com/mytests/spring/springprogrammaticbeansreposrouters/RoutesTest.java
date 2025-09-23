package com.mytests.spring.springprogrammaticbeansreposrouters;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.test.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("no-db")
public class RoutesTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void testAllpersons() throws Exception {
        mockMvc.perform(get("/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json("[{\"age\":20,\"firstName\":\"ivan\",\"id\":1,\"secondName\":\"ivanov\"},{\"age\":30,\"firstName\":\"petr\",\"id\":2,\"secondName\":\"petrov\"},{\"age\":40,\"firstName\":\"pavel\",\"id\":3,\"secondName\":\"pavlov\"}]"));
    }
}
