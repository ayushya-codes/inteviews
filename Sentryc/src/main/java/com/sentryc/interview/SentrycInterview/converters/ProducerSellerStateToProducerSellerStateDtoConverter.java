package com.sentryc.interview.SentrycInterview.converters;

import com.sentryc.interview.SentrycInterview.dtos.ProducerSellerStateDto;
import com.sentryc.interview.SentrycInterview.models.SellerProducerStateInfo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author Ayushya
 */
public class ProducerSellerStateToProducerSellerStateDtoConverter {

    public static List<ProducerSellerStateDto> convert(Set<SellerProducerStateInfo> sellerProducerStateInfos) {
        return sellerProducerStateInfos.stream().map(sellerProducerStateInfo -> {
            return ProducerSellerStateDto
                    .builder()
                    .producerName(sellerProducerStateInfo.getProducer().getName())
                    .sellerState(sellerProducerStateInfo.getState())
                    .sellerId(sellerProducerStateInfo.getSellerInfoId())
                    .producerId(sellerProducerStateInfo.getProducer().getId())
                    .build();
        }).collect(Collectors.toList());
    }

}
