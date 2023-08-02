package com.kolaykafe.kafebackend.menu.service;

import com.kolaykafe.kafebackend.menu.dto.MenuDTO;
import com.kolaykafe.kafebackend.menu.model.Menu;
import com.kolaykafe.kafebackend.menu.repository.IMenuRepository;
import com.kolaykafe.kafebackend.menu.service.interfaces.IMenuQueryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class MenuQueryServiceImp implements IMenuQueryService {
    @Autowired
    private IMenuRepository _menuRepo;
    @Autowired
    private ModelMapper _mapper;

    @Override
    public Stream<MenuDTO> getMenuByCafeId(Long cafeId) {
        try {
            List<Menu> menuList = Optional.of(_menuRepo.findByCafeId(cafeId).orElseThrow(() ->
                    new RuntimeException("Cafe does not found!"))).stream().toList();

            return menuList.stream().map(this::modelToDto);
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public Long getMenuItemIdByName(String itemName) {
        return _menuRepo.findByItemName(itemName).orElseThrow(() ->
                new RuntimeException("Item nor found")).getId();
    }

    private MenuDTO modelToDto(Menu obj) {
        return _mapper.map(obj, MenuDTO.class);
    }
}
