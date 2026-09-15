package com.pyeong.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "contents",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "unique_content",
                        columnNames = {"external_source", "external_id"}
                )
        }
)
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContentType type;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String imageUrl;

    private LocalDate releaseDate;

    @Column(length = 1000)
    private String description;

    @Column(length = 50)
    private String externalSource;

    @Column(length = 100)
    private String externalId;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    protected Content() {}
}
