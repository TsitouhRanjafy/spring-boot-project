package com.example.familycard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/cashcards")
public class CashCardController {

    @GetMapping(path = "/{id}")
    private ResponseEntity<String> findById(@PathVariable String id){
        return ResponseEntity.ok("{}");
    }
}
