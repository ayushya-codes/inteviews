package com.sentryc.interview.SentrycInterview.controllers.unir;

import com.sentryc.interview.SentrycInterview.controllers.SellerController;
import com.sentryc.interview.SentrycInterview.models.Seller;
import com.sentryc.interview.SentrycInterview.repositories.SellerRepository;
import com.sentryc.interview.SentrycInterview.services.SellerService;
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
@WebMvcTest(SellerController.class)
public class SellerControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SellerRepository sellerRepository;

    @MockBean
    private SellerService sellerService;

    @Test
    void getProducers() throws Exception {
        List<Seller> sellers = new ArrayList<>();
        sellers.add(getSeller());
        Mockito.when(sellerRepository.findAll(Mockito.any(Pageable.class))).thenReturn(new PageImpl<>(sellers));
        mockMvc.perform(MockMvcRequestBuilders.get("/sellers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(result -> {
                    Assertions.assertTrue(result.getResponse().getContentAsString().contains("India"));
                });
    }

    @Test
    void createProducer() throws Exception {

        Mockito.when(sellerRepository.save(Mockito.any(Seller.class))).thenReturn(getSeller());
        mockMvc.perform(MockMvcRequestBuilders.post("/sellers")
                        .content(TestUtils.convertToJson(getSeller()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(result -> {
                    Seller seller = TestUtils.convertToObject(result.getResponse().getContentAsString(), Seller.class);
                    Assertions.assertTrue("test_seller".equalsIgnoreCase(seller.getName()));
                });
    }

    private static Seller getSeller() {
        Seller seller = new Seller();
        seller.setCountry("India");
        seller.setName("test_seller");
        seller.setUrl("http://test.com");
        return seller;
    }
}