package com.kolaykafe.kafebackend.menu.repository;

import com.kolaykafe.kafebackend.menu.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IMenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByCafeId(Long cafeId);
}
