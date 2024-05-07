package com.sentryc.interview.SentrycInterview.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.validator.constraints.URL;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * @Author Ayushya
 */
@Entity
@Table(name = "seller_infos", schema = "public")
@Getter
@Setter
//REFERS to SELLER_INFOS TABLE
public class Seller implements Serializable {

    @Id
    @Column(name = "id")
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    private String id;

    @Column(name = "name", length = 2048)
    @NotBlank
    private String name;

    @Column(name = "url", length = 2048)
    @URL
    private String url;

    @Column(name = "country")
    private String country;

    @Column(name = "external_id")
    private String externalId;

    @OneToOne
    @JoinColumn(name = "marketplace_id", referencedColumnName = "id")
    private MarketPlace marketPlace;

    @OneToMany(targetEntity = SellerProducerStateInfo.class)
    @JoinColumn(name = "seller_info_id", referencedColumnName = "id")
    private Set<SellerProducerStateInfo> sellerProducerStateInfos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller that = (Seller) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
