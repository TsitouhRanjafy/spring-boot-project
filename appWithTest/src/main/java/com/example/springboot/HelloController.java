package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // that combine @Controller and @ResponseBody
public class HelloController {

  @GetMapping("/")
  public String index() {
    return "Greetings from Spring Boot!";
  }

}
