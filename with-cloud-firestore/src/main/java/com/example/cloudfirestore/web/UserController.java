package com.example.cloudfirestore.web;


import com.example.cloudfirestore.entity.User;
import com.example.cloudfirestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping(path = "/user")
    public Mono<User> createUser(@RequestBody User user){
        return this.userRepository.save(user);
    }

    @GetMapping(path = "/users")
    public Flux<User> getAllUser(@RequestParam int age){
        Flux<User> users = userRepository.findByAge(age);
        System.out.println(users);
        return users;
    }
}
