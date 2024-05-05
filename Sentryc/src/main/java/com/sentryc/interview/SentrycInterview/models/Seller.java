package com.sentryc.interview.SentrycInterview.models;

import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.Objects;

/**
 * @Author Ayushya
 */
@Entity
@Table(name = "sellers", uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {"producer_id", "seller_info_id", "state"}
        )
})
public class Seller {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    private String id;

    @OneToOne
    @JoinColumn(name = "producer_id")
    private Producer producer;

    @OneToOne
    @JoinColumn(name = "seller_info_id")
    private SellerInformation sellerInformation;

    @Column(name = "state")
    private String state = "REGULAR";


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public SellerInformation getSellerInformation() {
        return sellerInformation;
    }

    public void setSellerInformation(SellerInformation sellerInformation) {
        this.sellerInformation = sellerInformation;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return Objects.equals(getId(), seller.getId())
                && Objects.equals(getProducer(), seller.getProducer())
                && Objects.equals(getSellerInformation(), seller.getSellerInformation())
                && Objects.equals(getState(), seller.getState());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProducer(), getSellerInformation(), getState());
    }
}
