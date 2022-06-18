package com.example.restfulservice.user;

import com.example.restfulservice.exception.UserNotFoundException;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;


@RequestMapping("/admin/users")
@RestController
@RequiredArgsConstructor
public class AdminUserController {

    private final UserDaoService userService;

    @GetMapping
    public MappingJacksonValue retrieveAllUsers() {
        List<User> users = userService.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);
        return mapping;
    }

    @GetMapping(value = "/{id}", params = "version=1")
    public MappingJacksonValue retrieveUser(@PathVariable("id") int id) {
            try {
                User one = userService.findOne(id);

                SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                        .filterOutAllExcept("id","name","joinDate","ssn");

                FilterProvider filters =  new SimpleFilterProvider().addFilter("UserInfo",filter);

                MappingJacksonValue mapping = new MappingJacksonValue(one);
                mapping.setFilters(filters);

                return mapping;
            } catch (NoSuchElementException e) {
                throw new UserNotFoundException(String.format("ID[%s] not found", id), e);
            }
    }

    @GetMapping(value = "/{id}", params = "version=2")
    public MappingJacksonValue retrieveUserV2(@PathVariable("id") int id) {
        try {
            User one = userService.findOne(id);
            UserV2 v2 = new UserV2();
            BeanUtils.copyProperties(one, v2);
            v2.setGrade("VIP");

            SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                    .filterOutAllExcept("id","name","joinDate","ssn","grade");

            FilterProvider filters =  new SimpleFilterProvider().addFilter("UserInfoV2",filter);

            MappingJacksonValue mapping = new MappingJacksonValue(v2);
            mapping.setFilters(filters);

            return mapping;
        } catch (NoSuchElementException e) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id), e);
        }
    }
}


