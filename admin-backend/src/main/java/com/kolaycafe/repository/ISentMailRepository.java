package com.kolaycafe.repository;

import com.kolaycafe.model.SentMail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ISentMailRepository extends MongoRepository<SentMail, String> {
    Optional<SentMail> findById(String id);
    ArrayList<SentMail> findByStatusOrderBySentTimeAsc(boolean status);
}