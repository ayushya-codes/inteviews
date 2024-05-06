package com.sentryc.interview.SentrycInterview.repositories;

import com.sentryc.interview.SentrycInterview.dtos.Params;
import com.sentryc.interview.SentrycInterview.models.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author Ayushya
 */
public interface SellerRepositoryCustom {
    Page<Seller> findCustomSellerInformation(Params params, Pageable pageable);
}
