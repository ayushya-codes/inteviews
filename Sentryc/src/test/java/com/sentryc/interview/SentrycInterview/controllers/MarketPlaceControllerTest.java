package com.sentryc.interview.SentrycInterview.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sentryc.interview.SentrycInterview.models.MarketPlace;
import com.sentryc.interview.SentrycInterview.repositories.MarketPlaceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @Author Ayushya
 */
@SpringBootTest
@AutoConfigureMockMvc
class MarketPlaceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MarketPlaceRepository marketPlaceRepository;

    @Test
    void getMarketPlaces() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/marketplaces").contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void createMarketPlace() throws Exception {
        MarketPlace marketPlace = new MarketPlace();
        marketPlace.setId("TestId");
        marketPlace.setDescription("test_description");

        Mockito.when(marketPlaceRepository.save(Mockito.any(MarketPlace.class))).thenReturn(marketPlace);

        MvcResult result = mockMvc.perform(
                        MockMvcRequestBuilders.post("/marketplaces")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(convertToJson(marketPlace)))
                .andExpect(MockMvcResultMatchers.status().is(201)).andReturn();
        Mockito.verify(marketPlaceRepository, Mockito.times(1)).save(Mockito.any(MarketPlace.class));
        Assertions.assertTrue(result.getResponse().getContentAsString().contains("TestId"));

    }

    private String convertToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}