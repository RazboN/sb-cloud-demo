package com.kolaykafe.userproducer.repository;

import com.kolaykafe.userproducer.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
