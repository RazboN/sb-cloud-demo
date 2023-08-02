package com.kolaykafe.kafebackend.menu.service.interfaces;

import com.kolaykafe.kafebackend.menu.dto.MenuDTO;

import java.util.stream.Stream;

public interface IMenuQueryService {
    Stream<MenuDTO> getMenuByCafeId(Long cafeId);
    Long getMenuItemIdByName(String itemName);
}
