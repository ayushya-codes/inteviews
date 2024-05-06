package com.sentryc.interview.SentrycInterview.repositories;

import com.sentryc.interview.SentrycInterview.models.SellerProducerStateInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Ayushya
 */
@Repository
public interface SellerProducerStateInfoRepository extends JpaRepository<SellerProducerStateInfo, String> {
}
