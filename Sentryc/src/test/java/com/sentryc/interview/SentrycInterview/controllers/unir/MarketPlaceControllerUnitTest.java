package com.sentryc.interview.SentrycInterview.controllers.unir;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sentryc.interview.SentrycInterview.controllers.MarketPlaceController;
import com.sentryc.interview.SentrycInterview.models.MarketPlace;
import com.sentryc.interview.SentrycInterview.repositories.MarketPlaceRepository;
import com.sentryc.interview.SentrycInterview.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Ayushya
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(MarketPlaceController.class)
public class MarketPlaceControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MarketPlaceRepository marketPlaceRepository;

    @Test
    void getMarketPlaces() throws Exception {
        List<MarketPlace> marketPlaces = new ArrayList<>();
        marketPlaces.add(getMarketPlace());
        Mockito.when(marketPlaceRepository.findAll(Mockito.any(Pageable.class))).thenReturn(new PageImpl<>(marketPlaces));
        mockMvc.perform(MockMvcRequestBuilders.get("/marketplaces")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(result -> {
                    Assertions.assertTrue(result.getResponse().getContentAsString().contains("TestId"));
                });
    }

    @Test
    void createMarketPlace() throws Exception {

        Mockito.when(marketPlaceRepository.save(Mockito.any(MarketPlace.class))).thenReturn(getMarketPlace());
        mockMvc.perform(MockMvcRequestBuilders.post("/marketplaces")
                        .content(TestUtils.convertToJson(getMarketPlace()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(result -> {
                    MarketPlace marketPlace = new ObjectMapper().readValue(result.getResponse().getContentAsString(), MarketPlace.class);
                    Assertions.assertTrue("TestId".equalsIgnoreCase(marketPlace.getId()));
                });
    }

    private static MarketPlace getMarketPlace() {
        MarketPlace marketPlace = new MarketPlace();
        marketPlace.setId("TestId");
        marketPlace.setDescription("test_description");
        return marketPlace;
    }
}