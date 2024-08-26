package com.user.UserService.Service;


import com.user.UserService.Model.User;
import com.user.UserService.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createUser(User user) {
        // Hash the password before saving it (implement hashing here)
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        // Hash the password if it is being updated (implement hashing here)
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}