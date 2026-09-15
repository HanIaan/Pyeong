package com.pyeong.service;

import java.util.List;
import com.pyeong.dto.UserUpdateRequest;
import com.pyeong.domain.User;
import com.pyeong.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, UserUpdateRequest request) {

        User user = userRepository.findById(id)
                .orElse(null);

        if (user == null) {
            return null;
        }

        user.setNickname(request.getNickname());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
