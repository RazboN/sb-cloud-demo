package com.kolaykafe.kafebackend.menu.service;

import com.kolaykafe.kafebackend.menu.dto.MenuDTO;
import com.kolaykafe.kafebackend.menu.model.Menu;
import com.kolaykafe.kafebackend.menu.repository.IMenuRepository;
import com.kolaykafe.kafebackend.menu.service.interfaces.MenuCommandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuCommandServiceImp implements MenuCommandService {
    @Autowired
    private IMenuRepository _menuRepo;
    @Autowired
    private ModelMapper _mapper;

    @Override
    @Transactional
    public boolean addAndUpdateItemToMenu(MenuDTO newItemDTO) {
        Menu newItem = _mapper.map(newItemDTO, Menu.class);
        return _menuRepo.save(newItem) != null;
    }
}
