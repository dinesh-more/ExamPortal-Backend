package com.exam.controller;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repository.UserRepository;
import com.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping(value = "/createUser")
    public User createUser(@RequestBody User user) throws Exception {

        //Encoding password
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

        user.setProfile("default.png");

        Set<UserRole> roles = new HashSet<>();

        Role role = new Role();
        role.setRoleName("NORMAL");

        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);

        roles.add(userRole);

        return this.userService.createUser(user, roles);
    }

    @PutMapping(value = "/updateUser")
    public void updateUser(@RequestBody User user) throws Exception {
        this.userService.updateUser(user);
    }

    @GetMapping(value = "/{userName}")
    public User getUser(@PathVariable("userName") String userName) throws Exception {
        return this.userService.getUser(userName);
    }

    @GetMapping(value = "/user-list")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /*@GetMapping(value = "/user-list")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }*/

    @DeleteMapping(value = "/deleteUser/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        if (userId != null)
            this.userRepository.deleteById(userId);
    }

}
