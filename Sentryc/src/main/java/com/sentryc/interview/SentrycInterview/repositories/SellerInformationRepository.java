package com.sentryc.interview.SentrycInterview.repositories;

import com.sentryc.interview.SentrycInterview.models.SellerInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Ayushya
 */
@Repository
public interface SellerInformationRepository extends JpaRepository<SellerInformation, String> {
}
