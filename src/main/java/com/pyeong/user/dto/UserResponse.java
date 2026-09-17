package com.pyeong.user.dto;

import com.pyeong.user.entity.User;

import java.time.LocalDateTime;

public class UserResponse {

    private Long id;
    private String nickname;
    private String profileImageUrl;
    private String bio;
    private LocalDateTime createdAt;

    public UserResponse(
            Long id,
            String nickname,
            String profileImageUrl,
            String bio,
            LocalDateTime createdAt) {
        this.id = id;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.bio = bio;
        this.createdAt = createdAt;
    }

    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getNickname(),
                user.getProfileImageUrl(),
                user.getBio(),
                user.getCreatedAt()
        );
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getBio() {
        return bio;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
