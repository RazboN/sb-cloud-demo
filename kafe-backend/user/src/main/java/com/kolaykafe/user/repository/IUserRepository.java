package com.kolaykafe.user.repository;

import com.kolaykafe.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User fingByEmail(String email);
}
