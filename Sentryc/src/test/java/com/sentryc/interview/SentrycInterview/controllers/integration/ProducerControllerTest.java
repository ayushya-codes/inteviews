package com.sentryc.interview.SentrycInterview.controllers.integration;

import com.sentryc.interview.SentrycInterview.model.RestResponsePage;
import com.sentryc.interview.SentrycInterview.models.Producer;
import com.sentryc.interview.SentrycInterview.repositories.ProducerRepository;
import com.sentryc.interview.SentrycInterview.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.*;

/**
 * @Author Ayushya
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProducerControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProducerRepository producerRepository;

    private static HttpHeaders headers;

    @LocalServerPort
    private int localServerPort;

    @BeforeAll
    public static void setupHeaders() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    void getProducers() throws Exception {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<RestResponsePage<Producer>> response = restTemplate.exchange(
                "http://localhost:" + localServerPort + "/producers?size=10&page=0",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<RestResponsePage<Producer>>() {
                });
        PageImpl<Producer> pageObejctWithProducers = response.getBody();
        Assertions.assertNotNull(pageObejctWithProducers);
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(5, producerRepository.findAll().size());

    }

    @Test
    public void createProducer() throws Exception {
        Producer producer = new Producer();
        producer.setName("Integration Test Producer");

        HttpEntity<String> entity = new HttpEntity<>(TestUtils.convertToJson(producer), headers);
        ResponseEntity<Producer> response = restTemplate.exchange(
                "http://localhost:" + localServerPort + "/producers",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<Producer>() {
                });
        Producer savedProducer = response.getBody();
        Assertions.assertNotNull(savedProducer);
        Assertions.assertEquals(201, response.getStatusCodeValue());
        Assertions.assertEquals(6, producerRepository.findAll().size());
        // removing the test data for tests to behave
        producerRepository.delete(savedProducer);
    }


}