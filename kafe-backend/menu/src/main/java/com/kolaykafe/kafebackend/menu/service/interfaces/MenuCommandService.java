package com.kolaykafe.kafebackend.menu.service.interfaces;

import com.kolaykafe.kafebackend.menu.dto.MenuDTO;

public interface MenuCommandService {
    boolean addAndUpdateItemToMenu(MenuDTO newItemDTO);
}
