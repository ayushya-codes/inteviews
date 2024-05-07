package com.sentryc.interview.SentrycInterview.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Author Ayushya
 */
@Entity
@Table(name = "marketplaces")
@Getter
@Setter
public class MarketPlace implements Serializable {

    @Id
    // Allows to use latest RFC-4122 compliant with version 4
    @Column(name = "id")
    String id;

    @Column(name = "description")
    @Length(min = 0, max = 255) String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarketPlace that = (MarketPlace) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
