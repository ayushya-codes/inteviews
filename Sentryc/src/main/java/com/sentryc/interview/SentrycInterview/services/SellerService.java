package com.sentryc.interview.SentrycInterview.services;

import com.sentryc.interview.SentrycInterview.dtos.Data;
import com.sentryc.interview.SentrycInterview.dtos.Params;
import org.springframework.data.domain.Pageable;

/**
 * @Author Ayushya
 */
public interface SellerService {
    Data findCustomSellerInformation(Params params, Pageable pageable);
}
