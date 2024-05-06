package com.sentryc.interview.SentrycInterview.repositories;

import com.sentryc.interview.SentrycInterview.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author Ayushya
 */
public interface SellerRepository extends JpaRepository<Seller, String>, SellerRepositoryCustom {
}
