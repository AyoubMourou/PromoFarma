package com.example.PromoFarma.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Seller {

    //FIELDS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private LocalDateTime createdAt = LocalDateTime.now();

    //CONSTRUCTORS
    public Seller() {

    }

    public Seller(LocalDateTime createdAt, String name) {
        this.createdAt = createdAt;
        this.name = name;
    }

    // GETTERS-SETTERS
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
