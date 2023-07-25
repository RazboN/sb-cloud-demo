package com.kolaycafe.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kolaycafe.model.Cafe;
import com.kolaycafe.model.NewCafeInform;
import com.kolaycafe.repository.ICafeRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CafeService.class})
@ExtendWith(SpringExtension.class)
class CafeServiceTest {
    @Autowired
    private CafeService cafeService;

    @MockBean
    private EmailService emailService;

    @MockBean
    private ICafeRepository iCafeRepository;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    void testGetCafes() {
        ArrayList<Cafe> cafeList = new ArrayList<>();
        when(iCafeRepository.findAll()).thenReturn(cafeList);
        List<Cafe> actualCafes = cafeService.getCafes();
        assertSame(cafeList, actualCafes);
        assertTrue(actualCafes.isEmpty());
        verify(iCafeRepository).findAll();
    }

    @Test
    void testCreateCafe() throws Exception {
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date createDate = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        when(iCafeRepository.save((Cafe) any()))
                .thenReturn(new Cafe("42", createDate, "Test", "Istanbul",
                        "mahircanerdogan@hotmail.com",
                        true, UUID.randomUUID()));
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date createDate1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        when(iCafeRepository.findByName((String) any())).thenReturn(
                Optional.of(new Cafe("42", createDate1, "Test", "Istanbul",
                        "mahircanerdogan@hotmail.com",
                        true, UUID.randomUUID())));
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date createDate2 = Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        assertNull(cafeService
                .createCafe(new Cafe("42", createDate2, "Test", "Istanbul",
                        "mahircanerdogan@hotmail.com",
                        true, UUID.randomUUID())));
        verify(iCafeRepository).findByName((String) any());
    }

    @Test
    void testCreateCafe2() throws MessagingException {
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date createDate = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        Cafe cafe = new Cafe("42", createDate, "Test", "Istanbul",
                "mahircanerdogan@hotmail.com",
                true, UUID.randomUUID());

        when(iCafeRepository.save((Cafe) any())).thenReturn(cafe);
        when(iCafeRepository.findByName((String) any())).thenReturn(Optional.empty());
        when(emailService.informNewCafe((Object) any())).thenReturn(true);

        NewCafeInform newCafeInform = new NewCafeInform();
        newCafeInform.setCity("Istanbul");
        newCafeInform.setEmail("mahircanerdogan@hotmail.com");
        newCafeInform.setLicence(true);
        newCafeInform.setName("Test");
        newCafeInform.setPassword(UUID.randomUUID());
        when(modelMapper.map((Object) any(), (Class<NewCafeInform>) any())).thenReturn(newCafeInform);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date createDate1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        assertSame(cafe, cafeService
                .createCafe(new Cafe("42", createDate1, "Test", "Istanbul",
                        "mahircanerdogan@hotmail.com", true, UUID.randomUUID())));
        verify(iCafeRepository).save((Cafe) any());
        verify(iCafeRepository).findByName((String) any());
        verify(emailService).informNewCafe((Object) any());
        verify(modelMapper).map((Object) any(), (Class<NewCafeInform>) any());
    }

    @Test
    void testCreateCafe4() throws MessagingException {
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date createDate = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        Cafe cafe = new Cafe("42", createDate, "Name", "Oxford", "jane.doe@example.org", true, UUID.randomUUID());

        when(iCafeRepository.save((Cafe) any())).thenReturn(cafe);
        when(iCafeRepository.findByName((String) any())).thenReturn(Optional.empty());
        when(emailService.informNewCafe((Object) any())).thenReturn(false);

        NewCafeInform newCafeInform = new NewCafeInform();
        newCafeInform.setCity("Istanbul");
        newCafeInform.setEmail("mahircanerdogan@hotmail.com");
        newCafeInform.setLicence(true);
        newCafeInform.setName("Test");
        newCafeInform.setPassword(UUID.randomUUID());
        when(modelMapper.map((Object) any(), (Class<NewCafeInform>) any())).thenReturn(newCafeInform);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date createDate1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        assertSame(cafe, cafeService
                .createCafe(new Cafe("42", createDate1, "Test", "Istanbul",
                        "mahircanerdogan@hotmail.com", true, UUID.randomUUID())));
        verify(iCafeRepository).save((Cafe) any());
        verify(iCafeRepository).findByName((String) any());
        verify(emailService).informNewCafe((Object) any());
        verify(modelMapper).map((Object) any(), (Class<NewCafeInform>) any());
    }

    @Test
    void testDeleteCafe() {
        doNothing().when(iCafeRepository).deleteById((String) any());
        assertEquals("42", cafeService.deleteCafe("42"));
        verify(iCafeRepository).deleteById((String) any());
    }

    @Test
    void testGetCafeById() {
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date createDate = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        Cafe cafe = new Cafe("42", createDate, "Test", "Istanbul",
                "mahircanerdogan@hotmail.com", true, UUID.randomUUID());

        when(iCafeRepository.findById((String) any())).thenReturn(Optional.of(cafe));
        assertSame(cafe, cafeService.getCafeById("42"));
        verify(iCafeRepository).findById((String) any());
    }

    @Test
    void testGetCafeById2() {
        when(iCafeRepository.findById((String) any())).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> cafeService.getCafeById("42"));
        verify(iCafeRepository).findById((String) any());
    }

    @Test
    void testUpdateCafe() {
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date createDate = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        when(iCafeRepository.save((Cafe) any()))
                .thenReturn(new Cafe("42", createDate, "Test", "Istanbul",
                        "mahircanerdogan@hotmail.com", true, UUID.randomUUID()));
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date createDate1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        Cafe cafe = new Cafe("42", createDate1, "Test", "Istanbul",
                "jmahircanerdogan@hotmail.com", true, UUID.randomUUID());

        when(iCafeRepository.findById((String) any())).thenReturn(Optional.of(cafe));
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date createDate2 = Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        Cafe actualUpdateCafeResult = cafeService.updateCafe("42",
                new Cafe("42", createDate2, "Test", "Istanbul",
                        "mahircanerdogan@hotmail.com", true, UUID.randomUUID()));
        assertSame(cafe, actualUpdateCafeResult);
        assertEquals("Istanbul", actualUpdateCafeResult.getCity());
        assertTrue(actualUpdateCafeResult.isLicence());
        assertEquals("Test", actualUpdateCafeResult.getName());
        verify(iCafeRepository).save((Cafe) any());
        verify(iCafeRepository).findById((String) any());
    }

    @Test
    void testUpdateCafe3() {
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date createDate = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        when(iCafeRepository.save((Cafe) any()))
                .thenReturn(new Cafe("42", createDate, "Test", "Istanbul",
                        "mahircanerdogan@hotmail.com", true, UUID.randomUUID()));
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date createDate1 = Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant());
        Cafe cafe = new Cafe("42", createDate1, "Test", "Istanbul",
                "test mail", true, UUID.randomUUID());

        when(iCafeRepository.findById((String) any())).thenReturn(Optional.of(cafe));
        when(emailService.informNewCafe((Object) any())).thenReturn(true);

        NewCafeInform newCafeInform = new NewCafeInform();
        newCafeInform.setCity("Istanbul");
        newCafeInform.setEmail("mahircanerdogan@hotmail.com");
        newCafeInform.setLicence(true);
        newCafeInform.setName("Test");
        newCafeInform.setPassword(UUID.randomUUID());
        when(modelMapper.map((Object) any(), (Class<NewCafeInform>) any())).thenReturn(newCafeInform);
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date createDate2 = Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant());
        Cafe actualUpdateCafeResult = cafeService.updateCafe("42",
                new Cafe("42", createDate2, "Test", "Istanbul",
                        "mahircanerdogan@hotmail.com", true, UUID.randomUUID()));
        assertSame(cafe, actualUpdateCafeResult);
        assertEquals("Istanbul", actualUpdateCafeResult.getCity());
        assertTrue(actualUpdateCafeResult.isLicence());
        assertEquals("Test", actualUpdateCafeResult.getName());
        assertEquals("mahircanerdogan@hotmail.com", actualUpdateCafeResult.getEmail());
        verify(iCafeRepository).save((Cafe) any());
        verify(iCafeRepository).findById((String) any());
        verify(emailService).informNewCafe((Object) any());
        verify(modelMapper).map((Object) any(), (Class<NewCafeInform>) any());
    }
}

