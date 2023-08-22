package com.kolaycafe.service;

import com.kolaycafe.model.SentMail;
import com.kolaycafe.repository.ISentMailRepository;
import com.kolaycafe.service.abstracts.IEmailService;
import com.kolaycafe.utils.EmailUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private ISentMailRepository _sentMailRepo;

    @Override
    public boolean informNewCafe(Object obj) {
        try {
            emailUtil.sendInformationMail(obj);
            return true;
        }
        catch (MessagingException ex) {
            return false;
        }
    }

    @Override
    public void resendMail() throws Exception {
        try {
            ArrayList<SentMail> failedMailList = _sentMailRepo.findByStatusOrderBySentTimeAsc(false);
            SentMail failedMailObj = null;

            if (failedMailList.size() > 0) {
                failedMailObj = (SentMail) failedMailList.get(0);
                emailUtil.sendInformationMail(failedMailObj);
            }
        }
        catch (Exception ex) {
            throw new Exception("Hata");
        }
    }
}
