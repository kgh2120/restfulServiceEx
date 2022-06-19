package com.example.restfulservice.user;

import com.example.restfulservice.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


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
    public EntityModel<User> retrieveUser(@PathVariable("id") int id) {
            try {
                User user = userService.findOne(id);
                EntityModel<User> model = EntityModel.of(user);
                WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
                model.add(linkTo.withRel("all-users"));

                return model;
            } catch (NoSuchElementException e) {
                throw new UserNotFoundException(String.format("ID[%s] not found", id), e);
            }
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid UserForm userForm) {
        User saveUser = userService.save(userForm.createUser());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveUser.getId())
                .toUri();


        return ResponseEntity.created(location).build();
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        try {
            userService.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new UserNotFoundException(String.format("ID[%d] not found",id),e);
        }
    }
    @PutMapping("/{id}")
    public void updateUserName(@PathVariable int id, @RequestBody UserForm userForm) {
        try {
            userService.updateNameById(id,userForm);
        } catch (NoSuchElementException e) {
            throw new UserNotFoundException(String.format("ID[%d] not found",id),e);
        }
    }
}


