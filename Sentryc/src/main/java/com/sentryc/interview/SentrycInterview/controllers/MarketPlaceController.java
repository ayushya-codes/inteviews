package com.sentryc.interview.SentrycInterview.controllers;

import com.sentryc.interview.SentrycInterview.models.MarketPlace;
import com.sentryc.interview.SentrycInterview.repositories.MarketPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Ayushya
 */
@RestController
@RequestMapping(("/marketplaces"))
public class MarketPlaceController {

    @Autowired
    private MarketPlaceRepository marketPlaceRepository;



    @GetMapping
    public Page<MarketPlace> getMarketPlaces(Pageable pageable) {
        return marketPlaceRepository.findAll(pageable);
    }


    @PostMapping
    public ResponseEntity<MarketPlace> createMarketPlace(@RequestBody  MarketPlace marketPlace) {
        return new ResponseEntity<>(marketPlaceRepository.save(marketPlace), HttpStatus.CREATED);
    }
}
