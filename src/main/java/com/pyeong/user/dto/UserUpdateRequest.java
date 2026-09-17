package com.pyeong.user.dto;

import jakarta.validation.constraints.Size;

public class UserUpdateRequest {

    @Size(min = 2, max = 50)
    private String nickname;

    @Size(max = 500)
    private String profileImageUrl;

    @Size(max = 255)
    private String bio;

    public UserUpdateRequest() {}

    public String getNickname() {
        return nickname;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public String getBio() {
        return bio;
    }
}
