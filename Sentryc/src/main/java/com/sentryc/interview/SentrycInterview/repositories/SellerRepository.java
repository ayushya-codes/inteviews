package com.sentryc.interview.SentrycInterview.repositories;

import com.sentryc.interview.SentrycInterview.models.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @Author Ayushya
 */
@Repository
public interface SellerRepository extends JpaRepository<Seller, String> {
//    @Query("")
//    Page<Seller> findSellersByNameAndByProducerIdAndByMarketPlaceId(Pageable pageable);
}
