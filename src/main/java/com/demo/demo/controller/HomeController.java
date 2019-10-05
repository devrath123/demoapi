package com.demo.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class HomeController {

    @RequestMapping("/")
    public String home() {
        return "Hello World";
    }

    @GetMapping("/user")
    public Map<String, String> getAllUsers() {
        Map<String, String> userList = new HashMap<>();
        userList.put("name", "Dev");
        userList.put("name1", "Shalini");
        return userList;
    }

    @PostMapping("/adduser")
    public String addUser(@RequestBody String name){

        if (name.length() > 0){
            return name + "Added successfully";
        }else{
            return "Invalid user";
        }
    }

}
