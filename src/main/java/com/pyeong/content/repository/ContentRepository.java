package com.pyeong.content.repository;

import com.pyeong.content.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface ContentRepository extends JpaRepository<Content, Long>, JpaSpecificationExecutor {

    boolean existByExternalSourceAndExternalId(
            String externalSource, String externalId
    );

    Optional<Content> findByExternalSourceAndExternalId(
            String externalSource, String externalId
    );

    List<Content> findByType(ContentType type);

    List<Content> findByTitleContainingIgnoreCase(String title);
}
