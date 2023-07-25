package com.kolaycafe.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kolaycafe.exceptions.EntityExceptions;
import com.kolaycafe.model.Cafe;
import com.kolaycafe.repository.ICafeRepository;
import com.kolaycafe.service.CafeService;
import com.kolaycafe.service.EmailService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {KolayCafeController.class})
@ExtendWith(SpringExtension.class)
class KolayCafeControllerTest {
    @MockBean
    private CafeService cafeService;

    @MockBean
    private EmailService emailService;

    @MockBean
    private ICafeRepository iCafeRepository;

    @Autowired
    private KolayCafeController kolayCafeController;

    @Test
    void testGetCafes() throws Exception {
        when(cafeService.getCafes()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/cafe/all");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(kolayCafeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetCafes2() throws Exception {
        when(cafeService.getCafes()).thenThrow(new EntityExceptions("Error"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/cafe/all");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(kolayCafeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500));
    }

    @Test
    void testCareateCafe() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.get("/api/cafe")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date createDate = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult.content(objectMapper.writeValueAsString(
                new Cafe("42", createDate, "Test", "Istanbul", "mahircanerdogan@hotmail.com"
                        , true, UUID.randomUUID())));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(kolayCafeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }

    @Test
    void testDeleteCafe() throws Exception {
        when(cafeService.deleteCafe((String) any())).thenReturn("5");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/cafe/{id}",
                "5");
        MockMvcBuilders.standaloneSetup(kolayCafeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Deleted 5"));
    }

    @Test
    void testDeleteCafeEndsWithError() throws Exception {
        when(cafeService.deleteCafe((String) any())).thenThrow(new EntityExceptions("Error"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/cafe/{id}",
                "5");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(kolayCafeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Error"));
    }

    @Test
    void testCareateCafe2() throws Exception {
        java.sql.Date date = mock(java.sql.Date.class);
        when(date.getTime()).thenReturn(10L);
        Cafe value = new Cafe("42", date, "Test", "Istanbul",
                "mahircanerdogan@hotmail.com", true, UUID.randomUUID());

        String content = (new ObjectMapper()).writeValueAsString(value);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/v1/cafe")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(kolayCafeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(405));
    }

    @Test
    void testGetCafe() throws Exception {
        when(cafeService.getCafeById((String) any())).thenThrow(new EntityExceptions("Error"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/apicafe/{id}",
                "5");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(kolayCafeController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Error"));
    }
}

