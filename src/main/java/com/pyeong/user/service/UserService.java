package com.pyeong.user.service;

import com.pyeong.domain.StatusType;
import com.pyeong.user.dto.*;
import com.pyeong.user.entity.User;
import com.pyeong.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserCreateRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        if (userRepository.existsByNickname(request.getNickname())) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }

        User user = User.create(
                request.getEmail(),
                request.getPassword(),
                request.getNickname()
        );

        User savedUser = userRepository.save(user);

        return UserResponse.from(savedUser);
    }

    @Transactional(readOnly = true)
    public UserResponse getUser(Long id) {
        User user = userRepository.findByIdAndStatus(id, StatusType.ACTIVE)
                .orElseThrow(() ->
                    new IllegalArgumentException("존재하지 않는 사용자입니다.")
                );

        return UserResponse.from(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAllByStatus(StatusType.ACTIVE);

        return users.stream().map(UserResponse::from).toList();
    }

    public UserResponse updateUser(
            Long id,
            UserUpdateRequest request) {
        User user = userRepository.findByIdAndStatus(id, StatusType.ACTIVE)
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 사용자입니다.")
                );

        if (request.getNickname() != null
            && !request.getNickname().equals(user.getNickname())
            && userRepository.existsByNickname(request.getNickname())) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }

        user.updateProfile(
                request.getNickname(),
                request.getProfileImageUrl(),
                request.getBio()
        );

        return UserResponse.from(user);
    }


    public void deleteUser(Long id) {

        User user = userRepository.findByIdAndStatus(id, StatusType.ACTIVE)
                .orElseThrow(() ->
                        new IllegalArgumentException("존재하지 않는 사용자입니다.")
                );

        user.delete();
    }
}
