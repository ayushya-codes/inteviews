package com.sentryc.interview.SentrycInterview.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * @Author Ayushya
 */
@Entity
@Table(name = "producers")
@Getter
@Setter
public class Producer implements Serializable {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    @Length(min = 0, max = 255)
    private String name;

    @Column(name = "created_at")
    private Instant createdAt = Instant.now();


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
