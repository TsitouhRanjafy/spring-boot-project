package com.example.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest // lance l'orchestration, c'est comme lancer l'app normalement
@AutoConfigureMockMvc // configure automatiquement l'object MockMvc qui va jouer le rôle "faux client HTTP")
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getHello() throws Exception {
        mvc.perform(get("/").accept(MediaType.APPLICATION_JSON)) // on attend format json
                .andExpect(status().isOk()) // vérifier que le code repond avec 200 OK
                .andExpect(content().string(equalTo("Greetings from Spring Boot!"))); // vérifier le body response
    }
}
