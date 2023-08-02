package com.kolaykafe.kafebackend.menu.repository;

import com.kolaykafe.kafebackend.menu.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMenuRepository extends JpaRepository<Menu, Long> {
    Optional<Menu> findByCafeId(Long cafeId);
    Optional<Menu> findByItemName(String itemName);
}
