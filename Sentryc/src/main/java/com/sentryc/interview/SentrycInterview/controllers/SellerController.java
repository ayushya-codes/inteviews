package com.sentryc.interview.SentrycInterview.controllers;

import com.sentryc.interview.SentrycInterview.models.Seller;
import com.sentryc.interview.SentrycInterview.repositories.SellerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Ayushya
 */
@RestController
@RequestMapping("/sellers")
public class SellerController {

    private final SellerRepository sellerRepository;

    public SellerController(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }


    @GetMapping
    public Page<Seller> getSellers(Pageable pageable) {
        return sellerRepository.findAll(pageable);
    }

//    @GetMapping("/filter")
//    public Page<Seller> filterSellers(Pageable pageable) {
//        // TODO may introduce a service layer for additional business logic!
//        // i.e. Hibernate projections
//        return sellerRepository.findSellersByNameAndByProducerIdAndByMarketPlaceId(pageable);
//    }

    @PostMapping
    public Seller createSeller(Seller seller) {
        return sellerRepository.save(seller);
    }
}
