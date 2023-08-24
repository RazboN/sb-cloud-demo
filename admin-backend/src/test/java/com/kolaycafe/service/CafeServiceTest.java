package com.kolaycafe.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.kolaycafe.dto.CafeDTO;
import com.kolaycafe.model.Cafe;
import com.kolaycafe.repository.ICafeRepository;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class CafeServiceTest {
    @MockBean
    private ICafeRepository iCafeRepository;
    @MockBean
    private CafeService cafeService;

    @Test
    void testGetCafes() {
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

        when(iCafeRepository.findAll()).thenReturn(cafeList);
        assertNotNull(cafeList);
        assertTrue(cafeList.size() > 0);
    }

    @Test
    void testCreateCafe() throws Exception {
        Date createDate = new Date();
        Cafe testCafe = new Cafe("5", createDate, "Test", "Istanbul",
                "mahircanerdogan@hotmail.com",
                true, UUID.randomUUID());

        when(iCafeRepository.save((Cafe) any()))
                .thenReturn(testCafe);

        Cafe resultCafe = iCafeRepository.save(testCafe);

        assertNotNull(resultCafe);
    }

    @Test
    void testDeleteCafe() {
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

        when(iCafeRepository.findAll()).thenReturn(cafeList);

        iCafeRepository.deleteById("5");
        cafeList.remove(testCafe);

        assertTrue(iCafeRepository.findAll().equals(cafeList));
    }

    @Test
    void testGetCafeById() {
        Date createDate = new Date();
        Cafe testCafe = new Cafe("5", createDate, "Test", "Istanbul",
                "mahircanerdogan@hotmail.com",
                true, UUID.randomUUID());

        when(iCafeRepository.findById("5")).thenReturn(Optional.of(testCafe));

        assertNotNull(iCafeRepository.findById("5"));
        assertTrue(iCafeRepository.findById("5").equals(testCafe));
    }

    @Test
    void testGetCafeByIdWhenCafeNotFound() {
        when(iCafeRepository.findById("5")).thenReturn(null);

        Optional<Cafe> resultTest = iCafeRepository.findById("5");

        assertNull(resultTest);
    }

    @Test
    void testUpdateCafe() {
        Date createDate = new Date();
        Cafe oldCafe = new Cafe("5", createDate, "Test", "Istanbul",
                "mahircanerdogan@hotmail.com",
                true, UUID.randomUUID());

        CafeDTO newCafe = new CafeDTO("Test Yeni", "Test", "Istanbul",
                false);

        when(cafeService.getCafeById("5")).thenReturn(oldCafe);
        when(iCafeRepository.save(oldCafe)).thenReturn(oldCafe);

        oldCafe.setName(newCafe.getName());
        oldCafe.setCreateDate(new Date());
        oldCafe.setCity(newCafe.getCity());
        oldCafe.setLicence(newCafe.isLicence());

        Cafe resultTest = iCafeRepository.save(oldCafe);

        assertNotNull(resultTest);
        assertEquals(resultTest.getId(), "5");

    }
}

