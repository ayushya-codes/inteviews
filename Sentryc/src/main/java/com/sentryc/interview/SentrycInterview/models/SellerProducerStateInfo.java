package com.sentryc.interview.SentrycInterview.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
//REFERS to SELLER TABLE
public class SellerProducerStateInfo {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    private String id;

    @OneToOne
    @JoinColumn(name = "producer_id")
    private Producer producer;

    @OneToOne
    @JoinColumn(name = "seller_info_id")
    private Seller seller;

    @Column(name = "state")
    private String state = "REGULAR";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SellerProducerStateInfo that = (SellerProducerStateInfo) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
