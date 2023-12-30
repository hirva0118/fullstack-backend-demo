package com.CodingWithHirva.fullstack.controller;
import com.CodingWithHirva.fullstack.model.User;
import org.springframework.web.bind.annotation.*;
import com.CodingWithHirva.fullstack.exception.UserNotFoundException;
import java.util.List;
import java.util.ArrayList;


@RestController
@CrossOrigin("http://localhost:3000")
public class UserCOntroller {

    private List<User> userList;
    public UserCOntroller() {
        // Initialize the list with some data
        userList = new ArrayList<>();
        userList.add(new User(1,"John Doe", "john_doe123", "john.doe@example.com"));
        userList.add(new User(2,"Alice Smith", "alice_smith456", "alice.smith@example.com"));
        userList.add(new User(3,"Bob Johnson", "bob_johnson789", "bob.johnson@example.com"));
    }

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        if (userList.size() >= 1) {
            newUser.setId(userList.getLast().getId()+1);
        } else newUser.setId(1);
        userList.add(newUser);
        return newUser;
    }

    @GetMapping("/users")
    List<User> getAllUser(){
        return userList;
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        User user = userList.stream().filter(a -> a.getId()==id).findAny().orElse(null);
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        return user;
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        User user = userList.stream().filter(a -> a.getId()==id).findAny().orElse(null);
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        int i = userList.indexOf(user);
        userList.get(i).setName(newUser.getName());
        userList.get(i).setEmail(newUser.getEmail());
        userList.get(i).setUsername(newUser.getUsername());
        return userList.get(i);
    }

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        User user = userList.stream().filter(a-> a.getId()==id).findAny().orElse(null);
        if(user == null){
            throw new UserNotFoundException(id);
        }
        userList.remove(user);
        return "User with ID "+id+" has been deleted";
    }
}
