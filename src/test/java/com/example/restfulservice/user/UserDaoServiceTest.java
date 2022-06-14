package com.example.restfulservice.user;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class UserDaoServiceTest {

    UserDaoService service = new UserDaoService();

    @Test
    void findAll() {
        List<User> all = service.findAll();
        for (User user : all) {
            System.out.println(user);
        }
        int size = all.size();
        assertThat(size).isSameAs(3);
    }

    @Test
    void findOne() {
        User one = service.findOne(1);
        System.out.println(one);
        assertThat(one.getId()).isEqualTo(1);
    }

    @Test
    void save() {
        User save = service.save(new User(null, "테스터4", LocalDateTime.now()));
        System.out.println(save);
        assertThat(save.getId()).isEqualTo(4);
    }
}