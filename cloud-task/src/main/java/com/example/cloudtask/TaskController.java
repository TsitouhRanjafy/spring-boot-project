package com.example.cloudtask;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TaskController {

    @PostMapping("/tasks/hello")
    public ResponseEntity<Void> helloTask(@RequestBody String payload) {
        log.info("Task reçue ! Payload: {}", payload);
        return ResponseEntity.ok().build();
    }
}
