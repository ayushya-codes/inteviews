package com.sentryc.interview.SentrycInterview.controllers;

import com.sentryc.interview.SentrycInterview.models.MarketPlace;
import com.sentryc.interview.SentrycInterview.repositories.MarketPlaceRepository;
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
@RequestMapping(("/marketplaces"))
public class MarketPlaceController {

    private final MarketPlaceRepository marketPlaceRepository;

    public MarketPlaceController(MarketPlaceRepository marketPlaceRepository) {
        this.marketPlaceRepository = marketPlaceRepository;
    }


    @GetMapping
    public Page<MarketPlace> getMarketPlaces(Pageable pageable) {
        return marketPlaceRepository.findAll(pageable);
    }


    @PostMapping
    public MarketPlace createMarketPlace(MarketPlace marketPlace) {
        return marketPlaceRepository.save(marketPlace);
    }
}
