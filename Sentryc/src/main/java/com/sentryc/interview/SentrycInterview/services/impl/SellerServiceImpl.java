package com.sentryc.interview.SentrycInterview.services.impl;

import com.sentryc.interview.SentrycInterview.converters.SellerToSellerResponseDtoConverter;
import com.sentryc.interview.SentrycInterview.dtos.Data;
import com.sentryc.interview.SentrycInterview.dtos.Params;
import com.sentryc.interview.SentrycInterview.models.Seller;
import com.sentryc.interview.SentrycInterview.repositories.SellerRepository;
import com.sentryc.interview.SentrycInterview.services.SellerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @Author Ayushya
 */
@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }


    @Override
    public Data findCustomSellerInformation(Params params, Pageable pageable) {
        Page<Seller> page = sellerRepository.findCustomSellerInformation(params, pageable);
        Data data = new Data();
        data.setData(SellerToSellerResponseDtoConverter.convert(page.getContent()));
        return data;
    }
}
