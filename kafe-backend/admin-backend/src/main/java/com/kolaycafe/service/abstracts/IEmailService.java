package com.kolaycafe.service.abstracts;

import jakarta.mail.MessagingException;

public interface IEmailService {
    boolean informNewCafe(Object obj) throws MessagingException;
    void resendMail() throws Exception;
}
