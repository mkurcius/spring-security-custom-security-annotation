package com.example.demo.api;

import com.example.demo.security.hero.HeroOnly;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
  @HeroOnly
  @GetMapping("/protected")
  public String hello() {
    return "Hello (protected) world!";
  }

  @Secured("ROLE_USER")
  @GetMapping("/users")
  public String users() {
    return "Hello users!";
  }
}
