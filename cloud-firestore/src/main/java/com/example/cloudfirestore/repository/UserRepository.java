package com.example.cloudfirestore.repository;

import com.example.cloudfirestore.entity.User;
import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface UserRepository extends FirestoreReactiveRepository<User> {

    Flux<User> findByAge(int age);
}
