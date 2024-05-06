package com.sentryc.interview.SentrycInterview.dtos;

import lombok.Builder;
import lombok.Getter;

/**
 * @Author Ayushya
 */
@Getter
@Builder
public class ProducerSellerStateDto {
    private String producerId;
    private String producerName;
    private String sellerState;
    private String sellerId;
}
