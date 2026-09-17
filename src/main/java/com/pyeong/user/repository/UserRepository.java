package com.pyeong.user.repository;

import com.pyeong.domain.StatusType;
import com.pyeong.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    Optional<User> findByIdAndStatus(Long id, StatusType status);

    List<User> findAllByStatus(StatusType status);
}
