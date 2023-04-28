package com.example.springbootreactjsauctionapp.controllers;

import com.example.springbootreactjsauctionapp.models.User;
import com.example.springbootreactjsauctionapp.repositories.UserRepository;
import com.example.springbootreactjsauctionapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        System.out.println(id);
        return userRepository.findById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User newUser, @PathVariable(value = "id") Long id) {
        return this.userRepository.findById(id)
                .map(user -> {
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    user.setAddress(newUser.getAddress());
                    user.setPhoto(newUser.getPhoto());
                    user.setPhone(newUser.getPhone());
                    user.setCity(newUser.getCity());
                    user.setRegistration_date(newUser.getRegistration_date());
                    return this.userRepository.save(user);
                }).orElseGet(() -> {
                   newUser.setId(id);
                   return this.userRepository.save(newUser);
                });
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(item -> {
                    userRepository.deleteById(id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        User registeredUser = userService.registerUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
        System.out.println("register request: " + registeredUser);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<User> register(@RequestBody User user, Model model) {
        System.out.println("login request: " + user);
        User authenticated = userService.authenticate(user.getEmail(), user.getPassword());
        System.out.println("login AUTH: " + authenticated);
        if(authenticated != null) {
            //String authenticatedJson = objectMapper.writeValueAsString(authenticated);
            return ResponseEntity.ok(authenticated);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}