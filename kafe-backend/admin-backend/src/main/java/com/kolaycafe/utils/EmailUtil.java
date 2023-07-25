package com.kolaycafe.utils;

import com.kolaycafe.model.NewCafeInform;
import com.kolaycafe.model.SentMail;
import com.kolaycafe.repository.ISentMailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailUtil {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ISentMailRepository _mailRepo;
    private NewCafeInform cafeInformation = null;
    private SentMail sentMailInformation = null;

    public void sendInformationMail(Object obj) throws MessagingException {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setSubject("Set Password");
            String body = new String("");

            if (obj instanceof NewCafeInform) {
                cafeInformation = (NewCafeInform) obj;
                sentMailInformation = generateSentMailObj(cafeInformation);
                mimeMessageHelper.setTo(cafeInformation.getEmail());
                body = getMailBody(cafeInformation);
                sentMailInformation.setBody(body);
            }
            else {
                sentMailInformation = (SentMail) obj;
                body = sentMailInformation.getBody();
                mimeMessageHelper.setTo(sentMailInformation.getEmail());
            }

            mimeMessageHelper.setText(body);
            javaMailSender.send(mimeMessage);

            sentMailInformation.setStatus(true);
            _mailRepo.save(sentMailInformation);
        }
        catch (Exception ex) {
            sentMailInformation.setStatus(false);
            sentMailInformation.setError(ex.getMessage());

            _mailRepo.save(sentMailInformation);

            throw new MessagingException("Malil göderilemedi.");
        }
    }

    /*TODO
        Bu kısım daha merkezi ve jenerik olabilir
     */
    private String getMailBody(NewCafeInform cafeInformation) {
        return String.format("Sistemimize hoşgeldiniz." +
                "Email adresiniz kullanıcı adınız olup şifreniz aşağıdaki gibidir.\n" +
                "Şifre: %s", cafeInformation.getPassword());
    }

    private SentMail generateSentMailObj(NewCafeInform cafeInformation){
        return new SentMail(null,null,true, cafeInformation.getEmail(),null,null);
    }
}
