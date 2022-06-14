package com.example.restfulservice.user;

import com.example.restfulservice.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


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
            try {
                return userService.findOne(id);
            } catch (NoSuchElementException e) {
                throw new UserNotFoundException(String.format("ID[%s] not found", id), e);
            }
    }

    @PostMapping("/add")
    public void createUser(@RequestBody UserForm userForm) {
        userService.save(userForm.createUser());
    }


}


