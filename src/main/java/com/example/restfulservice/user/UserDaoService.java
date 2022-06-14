package com.example.restfulservice.user;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int sequence;
    static {
        users.add(new User(++sequence,"test"+sequence, LocalDateTime.now()));
        users.add(new User(++sequence,"test"+sequence, LocalDateTime.now()));
        users.add(new User(++sequence,"test"+sequence, LocalDateTime.now()));
   }

   public List<User> findAll() {
        return users;
   }

   public User findOne(int id) {
        return users.stream().filter(u -> u.getId() == id).findFirst().orElseThrow();
   }

   public User save(User user) {
       if (user.getId() == null) {
           user.setId(++sequence);
       }
       users.add(user);
       return user;
   }
}
