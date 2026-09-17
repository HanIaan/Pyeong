package com.pyeong.content.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "albums")
public class Album {

    @Id
    @Column(name = "content_id")
    private Long contentId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "content_id")
    private Content content;

    private String artist;

    protected Album() {
    }
}