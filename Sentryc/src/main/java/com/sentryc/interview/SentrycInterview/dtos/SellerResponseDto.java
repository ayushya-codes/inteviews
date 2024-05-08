package com.sentryc.interview.SentrycInterview.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Ayushya
 */
@Getter
@Builder
public class SellerResponseDto {
    private String sellerName;
    private String externalId;
    private String marketplaceId;
    private List<ProducerSellerStateDto> producerSellerStates = new ArrayList<>();
}
