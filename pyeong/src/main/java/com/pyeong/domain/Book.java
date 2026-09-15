package com.pyeong.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @Column(name = "content_id")
    private Long contentId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "content_id")
    private Content content;

    private String author;

    private String publisher;

    @Column(length = 20)
    private String isbn;

    protected Book() {
    }
}