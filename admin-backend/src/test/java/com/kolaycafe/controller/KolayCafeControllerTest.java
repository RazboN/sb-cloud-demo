package com.kolaycafe.controller;

import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.kolaycafe.model.Cafe;
import com.kolaycafe.service.CafeService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ExtendWith(MockitoExtension.class)
class KolayCafeControllerTest {

    @InjectMocks
    KolayCafeController cafeController;
    @Mock
    CafeService cafeService;

    @Test
    void testGetCafesWithData() throws Exception {
        Date createDate = new Date();
        Cafe testCafe = new Cafe("5", createDate, "Test", "Istanbul",
                "mahircanerdogan@hotmail.com",
                true, UUID.randomUUID());
        Cafe testCafe2 = new Cafe("6", createDate, "Test2", "Istanbul",
                "mahircanerdogan@hotmail.com",
                true, UUID.randomUUID());

        List<Cafe> cafeList = new ArrayList<>();
        cafeList.add(testCafe);
        cafeList.add(testCafe2);

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(cafeService.getCafes()).thenReturn(cafeList);

        ResponseEntity<List<Cafe>> resultTest = cafeController.getCafes();
        assertThat(resultTest.getStatusCode()).isEqualTo(OK);
        assertThat(resultTest.getBody()).isNotNull();
    }

    @Test
    void testGetCafesWithoutData() throws Exception {
        List<Cafe> cafeList = new ArrayList<>();

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(cafeService.getCafes()).thenReturn(cafeList);

        ResponseEntity<List<Cafe>> resultTest = cafeController.getCafes();
        assertThat(resultTest.getStatusCode()).isEqualTo(NOT_FOUND);
        assertThat(resultTest.getBody()).isNull();
    }

    @Test
    void testGetCafesWithInternalError() throws Exception {
        List<Cafe> cafeList = new ArrayList<>();

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(cafeService.getCafes()).thenThrow(MockitoException.class);

        ResponseEntity<List<Cafe>> resultTest = cafeController.getCafes();
        assertThat(resultTest.getStatusCode()).isEqualTo(INTERNAL_SERVER_ERROR);
    }
}

