package com.sentryc.interview.SentrycInterview.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Author Ayushya
 */
@Entity
@Table(name = "marketplaces")
public class MarketPlace implements Serializable {

    public MarketPlace() {}

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "description")
    @Length(min = 0, max = 255)
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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
