package com.example.simplerestapi.controller;

import com.example.simplerestapi.service.DataStoreService;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    private final DataStoreService dataStoreService;

    public HelloController(DataStoreService dataStoreService) {
        this.dataStoreService = dataStoreService;
    }

    @GetMapping("/")
    public String home() {
        return "Welcome to the Home Page!";
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }

    // New GET endpoint with a request parameter
    //e.g. curl "http://localhost:8080/contains?name=apple"
    @GetMapping("/contains")
    public boolean contains(@RequestParam(value = "name") String name) {
        return dataStoreService.contains(name);
    }

    // New POST endpoint with a request parameter
    //e.g. curl -X POST "http://localhost:8080/save" -d "item=apple"
    @PostMapping("/save")
    public String saveItem(@RequestParam(value = "item") String item) {
        return dataStoreService.saveItem(item);
    }

    // New POST endpoint with a request parameter
    //e.g. curl -X DELETE "http://localhost:8080/delete?item=apple"
    @DeleteMapping("/delete")
    public String deleteItem(@RequestParam(value = "item") String item) {
        return dataStoreService.deleteItem(item);
    }

    // New GET endpoint to get a random item
    //e.g. curl "http://localhost:8080/random"
    @GetMapping("/random")
    public String random() {
        return dataStoreService.randomItem();
    }
}
