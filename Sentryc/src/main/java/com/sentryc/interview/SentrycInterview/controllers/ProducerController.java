package com.sentryc.interview.SentrycInterview.controllers;

import com.sentryc.interview.SentrycInterview.models.Producer;
import com.sentryc.interview.SentrycInterview.repositories.ProducerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Ayushya
 */
@RestController
@RequestMapping("/producers")
public class ProducerController {

    private final ProducerRepository producerRepository;

    public ProducerController(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @GetMapping
    public Page<Producer> getAllProducers(Pageable pageable) {
        return producerRepository.findAll(pageable);
    }


    @PostMapping
    public ResponseEntity<Producer> createProducer(@RequestBody Producer producer) {
        return new ResponseEntity<>(producerRepository.save(producer), HttpStatus.CREATED);
    }
}
