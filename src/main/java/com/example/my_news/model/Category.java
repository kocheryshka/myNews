package com.example.my_news.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldNameConstants
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "short_desc", nullable = false)
    private String shortDesc;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<News> newsList = new ArrayList<>();

    @CreationTimestamp
    @Column(name = "create_dttm")
    private Instant createAt;

    @UpdateTimestamp
    @Column(name = "update_dttm")
    private Instant updateAt;

}
