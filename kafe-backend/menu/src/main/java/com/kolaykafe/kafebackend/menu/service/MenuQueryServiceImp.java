package com.kolaykafe.kafebackend.menu.service;

import com.kolaykafe.kafebackend.menu.dto.MenuDTO;
import com.kolaykafe.kafebackend.menu.model.Menu;
import com.kolaykafe.kafebackend.menu.repository.IMenuRepository;
import com.kolaykafe.kafebackend.menu.service.interfaces.MenuQueryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class MenuQueryServiceImp implements MenuQueryService {
    @Autowired
    private IMenuRepository _menuRepo;
    @Autowired
    private ModelMapper _mapper;

    @Override
    public Stream<MenuDTO> getMenuByCafeId(Long cafeId) {
        return _menuRepo.findByCafeId(cafeId).stream().map(this::modelToDto);
    }

    private MenuDTO modelToDto(Menu obj) {
        return _mapper.map(obj, MenuDTO.class);
        /**
        MenuDTO dto = new MenuDTO(obj.getUrunAdi(),
                obj.getIcerik(),
                obj.getFiyat(),
                obj.getAnaKategori(),
                obj.getAltKategori(),
                obj.getBaslik());

        return dto; **/
    }
}
