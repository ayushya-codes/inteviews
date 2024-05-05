package com.sentryc.interview.SentrycInterview.models;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;
import java.util.Objects;

/**
 * @Author Ayushya
 */
@Entity
@Table(name = "producers")
public class Producer {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    @Length(min = 0, max = 255)
    private String name;

    @Column(name = "created_at")
    private Instant createdAt;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    public void setCreatedAt(Instant createdAt) {
        this.createdAt = Instant.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producer producer = (Producer) o;
        return Objects.equals(getId(), producer.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
