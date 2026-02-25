package com.example.cloudfirestore.entity;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.firestore.annotation.PropertyName;
import com.google.cloud.spring.data.firestore.Document;

@Document(collectionName = "usersCollection")
public class User {
    @PropertyName("drink")
    public String favoriteDrink;
    @DocumentId
    private String id;
    private String name;
    private Integer age;

    public User() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

