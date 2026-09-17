package com.pyeong.user.entity;

import com.pyeong.domain.StatusType;

import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusType status = StatusType.ACTIVE;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true, length = 50)
    private String nickname;

    @Column(length = 500)
    private String profileImageUrl;

    @Column(length = 255)
    private String bio;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    protected User() {
    }

    public static User create(
            String email,
            String password,
            String nickname)
    {
        User user = new User();
        user.email = email;
        user.password = password;
        user.nickname = nickname;
        user.status = StatusType.ACTIVE;

        return user;
    }

    public void updateProfile(
            String nickname,
            String profileImageUrl,
            String bio
    ) {
        if (nickname != null) {
            this.nickname = nickname;
        }
        if (profileImageUrl != null) {
            this.profileImageUrl = profileImageUrl;
        }

        if (bio != null) {
            this.bio = bio;
        }
    }

    public void delete() {
        this.status = StatusType.DELETED;
        this.deletedAt = LocalDateTime.now();
    }
}
