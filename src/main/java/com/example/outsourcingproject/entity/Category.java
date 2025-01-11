package com.example.outsourcingproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.hibernate.annotations.Comment;

@Getter
@Table(name = "categories")
@Entity
public class Category {

    @Comment("카테고리 식별자")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT")
    private Long id;

    @Comment("카테고리 이름")
    @Column(
        name = "category_name",
        nullable = false
    )
    private String name;

    protected Category() {
    }

    public Category(
        String name
    ) {
        this.name = name;
    }
}
