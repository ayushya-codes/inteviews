package com.sentryc.interview.SentrycInterview.controllers.integration;

import com.sentryc.interview.SentrycInterview.model.RestResponsePage;
import com.sentryc.interview.SentrycInterview.models.MarketPlace;
import com.sentryc.interview.SentrycInterview.repositories.MarketPlaceRepository;
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
class MarketPlaceControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MarketPlaceRepository marketPlaceRepository;

    private static HttpHeaders headers;

    @LocalServerPort
    private int localServerPort;

    @BeforeAll
    public static void setupHeaders() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    void getMarketPlaces() throws Exception {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<RestResponsePage<MarketPlace>> response = restTemplate.exchange(
                "http://localhost:" + localServerPort + "/marketplaces?size=10&page=0",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<RestResponsePage<MarketPlace>>() {
                });
        PageImpl<MarketPlace> pageObejctWithMarketPlaces = response.getBody();
        Assertions.assertNotNull(pageObejctWithMarketPlaces);
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(5, marketPlaceRepository.findAll().size());

    }

    @Test
    public void createMarketPlace() throws Exception {
        MarketPlace marketPlace = new MarketPlace();
        marketPlace.setId("TestId");
        marketPlace.setDescription("test_description");

        HttpEntity<String> entity = new HttpEntity<>(TestUtils.convertToJson(marketPlace), headers);
        ResponseEntity<MarketPlace> response = restTemplate.exchange(
                "http://localhost:" + localServerPort + "/marketplaces",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<MarketPlace>() {
                });
        MarketPlace savedMarketPlace = response.getBody();
        Assertions.assertNotNull(savedMarketPlace);
        Assertions.assertEquals(201, response.getStatusCodeValue());
        Assertions.assertEquals(6, marketPlaceRepository.findAll().size());
        // removing the test data for tests to behave
        marketPlaceRepository.delete(savedMarketPlace);
    }


}