package com.sentryc.interview.SentrycInterview.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.Objects;

/**
 * @Author Ayushya
 */
@Entity
@Table(name = "sellers", schema = "public", uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {"producer_id", "seller_info_id", "state"}
        )
})
@Getter
@Setter
//REFERS to SELLER TABLE
public class SellerProducerStateInfo implements Serializable {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    private String id;

    @OneToOne
    @JoinColumn(name = "producer_id")
    private Producer producer;

    @Column(name = "state")
    // default state is regular!
    private String state = "REGULAR";

    @Column(name = "seller_info_id")
    private String sellerInfoId;

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
