package com.assignments.projectmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignments.projectmanagement.jpa.UserRepository;
import com.assignments.projectmanagement.model.User;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/user")
    public User create(@RequestBody User user) throws Exception {
        return userRepository.save(user);
    }

    @GetMapping(path = "/users")
    public List<User> getProjects() {
        return userRepository.findAll();
    }

}
