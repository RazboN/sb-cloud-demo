package com.kolaycafe.repository;

import com.kolaycafe.model.Cafe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICafeRepository extends MongoRepository<Cafe,String> {
    Optional<Cafe> findByName(String name);

}
