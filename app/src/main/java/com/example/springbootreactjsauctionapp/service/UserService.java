package com.example.springbootreactjsauctionapp.service;

import com.example.springbootreactjsauctionapp.models.User;
import com.example.springbootreactjsauctionapp.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User registerUser(String firstName, String lastName, String email, String password) {
        if(firstName != null && password != null && lastName != null && email != null) {
            if(userRepository.findFirstByEmail(email).isPresent()) {
                System.out.println("Duplicate login");
                return null;
            }
            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPassword(password);
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public User authenticate(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password).orElse(null);
    }
}
