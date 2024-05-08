package com.sentryc.interview.SentrycInterview.controllers.integration;

import com.sentryc.interview.SentrycInterview.model.RestResponsePage;
import com.sentryc.interview.SentrycInterview.models.Seller;
import com.sentryc.interview.SentrycInterview.repositories.SellerRepository;
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
class SellerControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SellerRepository sellerRepository;

    private static HttpHeaders headers;

    @LocalServerPort
    private int localServerPort;

    @BeforeAll
    public static void setupHeaders() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    void getSellers() throws Exception {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<RestResponsePage<Seller>> response = restTemplate.exchange(
                "http://localhost:" + localServerPort + "/sellers?size=10&page=0",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<RestResponsePage<Seller>>() {
                });
        PageImpl<Seller> pageObejctWithMarketPlaces = response.getBody();
        Assertions.assertNotNull(pageObejctWithMarketPlaces);
        Assertions.assertEquals(response.getStatusCodeValue(), 200);
        Assertions.assertEquals(5, sellerRepository.findAll().size());

    }

    @Test
    public void createSellers () throws Exception {
        Seller seller = new Seller();
        seller.setCountry("India");
        seller.setName("test_seller");
        seller.setUrl("http://test.com");

        HttpEntity<String> entity = new HttpEntity<>(TestUtils.convertToJson(seller), headers);
        ResponseEntity<Seller> response = restTemplate.exchange(
                "http://localhost:" + localServerPort + "/sellers",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<Seller>() {
                });
        Seller savedSeller = response.getBody();
        Assertions.assertNotNull(savedSeller);
        Assertions.assertEquals(response.getStatusCodeValue(), 201);
        Assertions.assertEquals(6, sellerRepository.findAll().size());
        // removing the test data for tests to behave
        sellerRepository.delete(savedSeller);
    }


}