package com.sentryc.interview.SentrycInterview.repositories;

import com.sentryc.interview.SentrycInterview.models.MarketPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Ayushya
 */
@Repository
public interface MarketPlaceRepository extends JpaRepository<MarketPlace, String> {
}
