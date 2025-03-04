package com.example.simplerestapi.service;

import com.example.simplerestapi.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {

    private final Map<String, User> userStore;

    public UserService() {
        userStore = new HashMap<>();
    }

    public String saveUser(User user){
        String key = user.getFirstName() + "_" + user.getLastName();
        if (!userStore.containsKey(key)) {
            userStore.put(key, user);
            return "User Added";
        }else{
            return "User Already Exists";
        }
    }

    public User getUserByName(String firstName, String lastName){
        String key = firstName + "_" + lastName;
        return userStore.get(key);
    }
}
