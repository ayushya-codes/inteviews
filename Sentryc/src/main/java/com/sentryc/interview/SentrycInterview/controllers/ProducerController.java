package com.sentryc.interview.SentrycInterview.controllers;

import com.sentryc.interview.SentrycInterview.models.Producer;
import com.sentryc.interview.SentrycInterview.repositories.ProducerRepository;
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
    public Producer createProducer(Producer producer) {
        return producerRepository.save(producer);
    }
}
