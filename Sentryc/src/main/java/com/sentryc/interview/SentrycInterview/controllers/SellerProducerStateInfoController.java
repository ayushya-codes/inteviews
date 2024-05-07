package com.sentryc.interview.SentrycInterview.controllers;

import com.sentryc.interview.SentrycInterview.models.SellerProducerStateInfo;
import com.sentryc.interview.SentrycInterview.repositories.SellerProducerStateInfoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Ayushya
 */
@RestController
@RequestMapping("/states")
public class SellerProducerStateInfoController {

    private final SellerProducerStateInfoRepository sellerProducerStateInfoRepository;

    public SellerProducerStateInfoController(SellerProducerStateInfoRepository sellerProducerStateInfoRepository) {
        this.sellerProducerStateInfoRepository = sellerProducerStateInfoRepository;
    }

    @GetMapping
    public Page<SellerProducerStateInfo> getProducerStateInfos(Pageable pageable) {
        return sellerProducerStateInfoRepository.findAll(pageable);
    }

    @PostMapping
    public SellerProducerStateInfo createAssociation(@RequestBody SellerProducerStateInfo sellerProducerStateInfo) {
        return sellerProducerStateInfoRepository.save(sellerProducerStateInfo);
    }

}
