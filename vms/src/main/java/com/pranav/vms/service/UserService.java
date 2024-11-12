package com.pranav.vms.service;

import com.pranav.vms.model.User;
import com.pranav.vms.repository.user_repo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class UserService {

    private final user_repo userRepository;

    @Autowired
    public UserService(user_repo userRepository) {
        this.userRepository = userRepository;
    }

    // Save User with validation
    public User saveUser(@Valid User user) {
        return userRepository.save(user);
    }

    // Get User by ID
    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    // Get all Users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Update User by ID
    public User updateUser(String id, @Valid User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setFullName(userDetails.getFullName());
                    user.setEmail(userDetails.getEmail());
                    user.setPhoneNumber(userDetails.getPhoneNumber());
                    user.setAddress(userDetails.getAddress());
                    user.setDateOfBirth(userDetails.getDateOfBirth());
                    user.setEmergencyContacts(userDetails.getEmergencyContacts());
                    user.setPreferredTeams(userDetails.getPreferredTeams());
                    user.setAvailability(userDetails.getAvailability());
                    user.setSkills(userDetails.getSkills());
                    user.setAdditionalComments(userDetails.getAdditionalComments());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }
}
