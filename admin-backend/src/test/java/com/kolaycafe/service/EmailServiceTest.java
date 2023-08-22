package com.kolaycafe.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kolaycafe.model.SentMail;
import com.kolaycafe.repository.ISentMailRepository;
import com.kolaycafe.utils.EmailUtil;

import java.sql.Timestamp;

import java.util.ArrayList;


import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmailService.class})
@ExtendWith(SpringExtension.class)
class EmailServiceTest {
    @Autowired
    private EmailService emailService;

    @MockBean
    private EmailUtil emailUtil;

    @MockBean
    private ISentMailRepository iSentMailRepository;

    @MockBean
    private JavaMailSender javaMailSender;

    @Test
    void testInformNewCafe() throws MessagingException {
        doNothing().when(emailUtil).sendInformationMail((Object) any());
        assertTrue(emailService.informNewCafe("Obj"));
        verify(emailUtil).sendInformationMail((Object) any());
    }

    @Test
    void testResendMail() throws Exception {
        when(iSentMailRepository.findByStatusOrderBySentTimeAsc(anyBoolean())).thenReturn(new ArrayList<>());
        emailService.resendMail();
        verify(iSentMailRepository).findByStatusOrderBySentTimeAsc(anyBoolean());
    }

    @Test
    void testResendMail2() throws Exception {
        ArrayList<SentMail> sentMailList = new ArrayList<>();
        sentMailList.add(new SentMail("42", "Test maili", true, "mahircanerdogan@hotmail.com",
                "Hata", mock(Timestamp.class)));
        when(iSentMailRepository.findByStatusOrderBySentTimeAsc(anyBoolean())).thenReturn(sentMailList);
        doNothing().when(emailUtil).sendInformationMail((Object) any());
        emailService.resendMail();
        verify(iSentMailRepository).findByStatusOrderBySentTimeAsc(anyBoolean());
        verify(emailUtil).sendInformationMail((Object) any());
    }

    @Test
    void testResendMail3() throws Exception {
        ArrayList<SentMail> sentMailList = new ArrayList<>();
        sentMailList.add(new SentMail("42", "Test maili", true, "mahircanerdogan@hotmail.com",
                "Hata", mock(Timestamp.class)));
        when(iSentMailRepository.findByStatusOrderBySentTimeAsc(anyBoolean())).thenReturn(sentMailList);
        doThrow(new MessagingException()).when(emailUtil).sendInformationMail((Object) any());
        assertThrows(Exception.class, () -> emailService.resendMail());
        verify(iSentMailRepository).findByStatusOrderBySentTimeAsc(anyBoolean());
        verify(emailUtil).sendInformationMail((Object) any());
    }
}

