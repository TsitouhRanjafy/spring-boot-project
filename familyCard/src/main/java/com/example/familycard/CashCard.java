package com.example.familycard;


/*
*
* record is special form of class , java generate automatically a:
*   - constructor
*   - getter and setter
*   - equals()
*   - hashCode()
*   - toString()
*
* It is perfect for a class that not hava a complexe logic métier (DTO, API response, projection, json object)
* But class is preferred for entity JPA, modifiable object
* */
public record CashCard(Long id, Double amount) { }
