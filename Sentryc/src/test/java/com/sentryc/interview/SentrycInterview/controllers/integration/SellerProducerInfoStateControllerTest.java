package com.sentryc.interview.SentrycInterview.controllers.integration;

import com.sentryc.interview.SentrycInterview.model.RestResponsePage;
import com.sentryc.interview.SentrycInterview.models.SellerProducerStateInfo;
import com.sentryc.interview.SentrycInterview.repositories.SellerProducerStateInfoRepository;
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
class SellerProducerInfoStateControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private SellerProducerStateInfoRepository sellerProducerStateInfoRepository;

    private static HttpHeaders headers;

    @LocalServerPort
    private int localServerPort;

    @BeforeAll
    public static void setupHeaders() {
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    void getSellerProducerStateInfos() throws Exception {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<RestResponsePage<SellerProducerStateInfo>> response = restTemplate.exchange(
                "http://localhost:" + localServerPort + "/states?size=10&page=0",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<RestResponsePage<SellerProducerStateInfo>>() {
                });
        PageImpl<SellerProducerStateInfo> pageObejctWithMarketPlaces = response.getBody();
        Assertions.assertNotNull(pageObejctWithMarketPlaces);
        Assertions.assertEquals(200, response.getStatusCodeValue() );
        Assertions.assertEquals(5, sellerProducerStateInfoRepository.findAll().size());

    }

    @Test
    public void c() throws Exception {
        SellerProducerStateInfo sellerProducerStateInfo = new SellerProducerStateInfo();
        sellerProducerStateInfo.setState("Test_State");


        HttpEntity<String> entity = new HttpEntity<>(TestUtils.convertToJson(sellerProducerStateInfo), headers);
        ResponseEntity<SellerProducerStateInfo> response = restTemplate.exchange(
                "http://localhost:" + localServerPort + "/states",
                HttpMethod.POST,
                entity,
                new ParameterizedTypeReference<SellerProducerStateInfo>() {
                });
        SellerProducerStateInfo savedSellerProducerStateInfo = response.getBody();
        Assertions.assertNotNull(savedSellerProducerStateInfo);
        Assertions.assertEquals(201, response.getStatusCodeValue());
        Assertions.assertEquals(6, sellerProducerStateInfoRepository.findAll().size());
        // removing the test data for tests to behave
        sellerProducerStateInfoRepository.delete(savedSellerProducerStateInfo);
    }


}