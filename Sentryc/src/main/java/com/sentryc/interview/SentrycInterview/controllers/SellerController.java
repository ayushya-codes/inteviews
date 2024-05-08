package com.sentryc.interview.SentrycInterview.controllers;

import com.sentryc.interview.SentrycInterview.dtos.Data;
import com.sentryc.interview.SentrycInterview.dtos.Params;
import com.sentryc.interview.SentrycInterview.models.Seller;
import com.sentryc.interview.SentrycInterview.repositories.SellerRepository;
import com.sentryc.interview.SentrycInterview.services.SellerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Ayushya
 */
@RestController
@RequestMapping("/sellers")
public class SellerController {

    private final SellerRepository sellerRepository;

    private final SellerService sellerService;

    public SellerController(SellerRepository sellerRepository, SellerService sellerService) {
        this.sellerRepository = sellerRepository;
        this.sellerService = sellerService;
    }


    @GetMapping
    public Page<Seller> getSellers(Pageable pageable) {
        return sellerRepository.findAll(pageable);
    }

    @GetMapping("/filter")
    public Data filterSellers(Params params, Pageable pageable) {
        return sellerService.findCustomSellerInformation(params, pageable);
    }

    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) {
        return new ResponseEntity<>(sellerRepository.save(seller), HttpStatus.CREATED);
    }
}
