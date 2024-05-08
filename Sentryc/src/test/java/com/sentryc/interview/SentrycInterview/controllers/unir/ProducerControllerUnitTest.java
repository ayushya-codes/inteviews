package com.sentryc.interview.SentrycInterview.controllers.unir;

import com.sentryc.interview.SentrycInterview.controllers.ProducerController;
import com.sentryc.interview.SentrycInterview.models.Producer;
import com.sentryc.interview.SentrycInterview.repositories.ProducerRepository;
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

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Ayushya
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(ProducerController.class)
public class ProducerControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProducerRepository producerRepository;

    @Test
    void getProducers() throws Exception {
        List<Producer> producers = new ArrayList<>();
        producers.add(getProducer());
        Mockito.when(producerRepository.findAll(Mockito.any(Pageable.class))).thenReturn(new PageImpl<>(producers));
        mockMvc.perform(MockMvcRequestBuilders.get("/producers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andDo(result -> {
                    Assertions.assertTrue(result.getResponse().getContentAsString().contains("Test123"));
                });
    }

    @Test
    void createProducer() throws Exception {

        Mockito.when(producerRepository.save(Mockito.any(Producer.class))).thenReturn(getProducer());
        mockMvc.perform(MockMvcRequestBuilders.post("/producers")
                        .content(TestUtils.convertToJson(getProducer()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(result -> {
                    Producer producer = TestUtils.convertToObject(result.getResponse().getContentAsString(), Producer.class);
                    Assertions.assertTrue("Test123".equalsIgnoreCase(producer.getId()));
                });
    }

    private static Producer getProducer() {
        Producer producer = new Producer();
        producer.setId("Test123");
        producer.setName("Integration Test Producer");
        producer.setCreatedAt(Instant.now());
        return producer;
    }
}