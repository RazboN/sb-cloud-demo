package com.kolaykafe.kafebackend.menu.service.interfaces;

import com.kolaykafe.kafebackend.menu.dto.MenuDTO;

import java.util.stream.Stream;

public interface MenuQueryService {
    Stream<MenuDTO> getMenuByCafeId(Long cafeId);
}
