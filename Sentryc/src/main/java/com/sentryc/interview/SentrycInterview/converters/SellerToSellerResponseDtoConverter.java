package com.sentryc.interview.SentrycInterview.converters;

import com.sentryc.interview.SentrycInterview.dtos.SellerResponseDto;
import com.sentryc.interview.SentrycInterview.models.Seller;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Ayushya
 */

// WE can use MapStruct here for avoiding this code!
public class SellerToSellerResponseDtoConverter {
    public static List<SellerResponseDto> convert(List<Seller> sellers) {
        // There are some custom mappings required and hence this won't work!
        //        return sellers.stream().map(SellerResponseDto::new).collect(Collectors.toList());

        List<SellerResponseDto> sellerResponseDtos = new ArrayList<>();
        for (Seller seller : sellers) {

            SellerResponseDto sellerResponseDto = SellerResponseDto
                    .builder()
                    .sellerName(seller.getName())
                    .externalId(seller.getExternalId())
                    .marketplaceId(seller.getMarketPlace().getId())
                    .producerSellerStates(
                            ProducerSellerStateToProducerSellerStateDtoConverter
                                    .convert(seller.getSellerProducerStateInfos()))
                    .build();
            sellerResponseDtos.add(sellerResponseDto);
        }

        return sellerResponseDtos;
    }
}
