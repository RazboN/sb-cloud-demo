package com.kolaycafe.jobs;

import com.kolaycafe.repository.ISentMailRepository;
import com.kolaycafe.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
@Slf4j
public class FailedToSendEmailChecker {
    @Autowired
    private ISentMailRepository _sentMailRepo;
    @Autowired
    private EmailService _emailService;

    @Scheduled(fixedRate = 5000)
    @Async
    public void ResendFailedMail() throws Exception {
        log.info("ResendFailedMail call");
        _emailService.resendMail();
    }
}
