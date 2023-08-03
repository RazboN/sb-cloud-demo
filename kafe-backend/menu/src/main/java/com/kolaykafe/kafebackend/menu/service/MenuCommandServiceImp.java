package com.kolaykafe.kafebackend.menu.service;

import com.kolaykafe.kafebackend.menu.dto.MenuDTO;
import com.kolaykafe.kafebackend.menu.model.Menu;
import com.kolaykafe.kafebackend.menu.repository.IMenuRepository;
import com.kolaykafe.kafebackend.menu.service.interfaces.IMenuCommandService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuCommandServiceImp implements IMenuCommandService {
    @Autowired
    private IMenuRepository _menuRepo;
    @Autowired
    private ModelMapper _mapper;

    @Override
    @Transactional
    public boolean addAndUpdateItemToMenu(MenuDTO newItemDTO) {
        try {
            Menu newItem = new Menu();
            newItem.setItemName(newItemDTO.getItemName());
            newItem.setPrice(newItemDTO.getPrice());
            newItem.setMainCategory(newItem.getMainCategory());
            newItem.setSubCategory(newItem.getSubCategory());
            newItem.setCafeId(newItemDTO.getCafeId());

            _menuRepo.save(newItem);
            return true;
        }
        catch (Exception ex) {
            return false;
        }
    }
}
