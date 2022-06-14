package com.example.restfulservice.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserDaoService userService;

    @GetMapping
    public List<User> retrieveAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User retrieveUser(@PathVariable("id") int id) {
        return userService.findOne(id);
    }

    @PostMapping("/add")
    public void createUser(@RequestBody UserForm userForm) {
        userService.save(userForm.createUser());
    }


}


