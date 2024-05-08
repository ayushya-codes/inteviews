package com.sentryc.interview.SentrycInterview.controllers.unir;

import com.sentryc.interview.SentrycInterview.controllers.SellerProducerStateInfoController;
import com.sentryc.interview.SentrycInterview.models.SellerProducerStateInfo;
import com.sentryc.interview.SentrycInterview.repositories.SellerProducerStateInfoRepository;
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
@WebMvcTest(SellerProducerStateInfoController.class)
public class SellerProducerInfoStateControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SellerProducerStateInfoRepository sellerProducerStateInfoRepository;

    @Test
    void getProducers() throws Exception {
        List<SellerProducerStateInfo> sellerProducerStateInfos = new ArrayList<>();
        sellerProducerStateInfos.add(getSellerProducerStateInfo());
        Mockito.when(sellerProducerStateInfoRepository.findAll(Mockito.any(Pageable.class))).thenReturn(new PageImpl<>(sellerProducerStateInfos));
        mockMvc.perform(MockMvcRequestBuilders.get("/states")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(result -> {
                    Assertions.assertTrue(result.getResponse().getContentAsString().contains("stateId-789"));
                });
    }

    @Test
    void createProducer() throws Exception {

        Mockito.when(sellerProducerStateInfoRepository.save(Mockito.any(SellerProducerStateInfo.class))).thenReturn(getSellerProducerStateInfo());
        mockMvc.perform(MockMvcRequestBuilders.post("/states")
                        .content(TestUtils.convertToJson(getSellerProducerStateInfo()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(result -> {
                    SellerProducerStateInfo stateInfo = TestUtils.convertToObject(result.getResponse().getContentAsString(), SellerProducerStateInfo.class);
                    Assertions.assertEquals("stateId-789", stateInfo.getId());
                    Assertions.assertEquals("Test_State", stateInfo.getState());
                });
    }

    private static SellerProducerStateInfo getSellerProducerStateInfo() {
        SellerProducerStateInfo sellerProducerStateInfo = new SellerProducerStateInfo();
        sellerProducerStateInfo.setState("Test_State");
        sellerProducerStateInfo.setSellerInfoId("TestID-123");
        sellerProducerStateInfo.setId("stateId-789");
        return sellerProducerStateInfo;
    }
}