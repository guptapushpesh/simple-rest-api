package com.example.simplerestapi.controller;


import com.example.simplerestapi.model.User;
import com.example.simplerestapi.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(){
        this.userService = new UserService();
    }

    @PostMapping("/user")
    public String createUser(@RequestBody User user) {
        userService.saveUser(user);
        return "User created: " + user.toString();
    }

    @GetMapping("/user")
    public User getUser(@RequestParam String firstName, @RequestParam String lastName){
        return userService.getUserByName(firstName, lastName);
    }
}
