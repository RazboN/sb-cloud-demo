package com.kolaykafe.kafebackend.menu.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kolaykafe.kafebackend.menu.dto.MenuDTO;
import com.kolaykafe.kafebackend.menu.model.Menu;
import com.kolaykafe.kafebackend.menu.repository.IMenuRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MenuCommandServiceImp.class})
@ExtendWith(SpringExtension.class)
class MenuCommandServiceImpTest {
    @MockBean
    private IMenuRepository iMenuRepository;

    @Autowired
    private MenuCommandServiceImp menuCommandServiceImp;

    @MockBean
    private ModelMapper modelMapper;

    /**
     * Method under test: {@link MenuCommandServiceImp#addAndUpdateItemToMenu(MenuDTO)}
     */
    @Test
    void testAddAndUpdateItemToMenu() {
        Menu menu = new Menu();
        menu.setAltKategori("Alt Kategori");
        menu.setAnaKategori("Ana Kategori");
        menu.setBaslik("Baslik");
        menu.setCafeId(123L);
        menu.setFiyat(1);
        menu.setIcerik("Icerik");
        menu.setId(123L);
        menu.setUrunAdi("Urun Adi");
        when(modelMapper.map((Object) any(), (Class<Menu>) any())).thenReturn(menu);

        Menu menu1 = new Menu();
        menu1.setAltKategori("Alt Kategori");
        menu1.setAnaKategori("Ana Kategori");
        menu1.setBaslik("Baslik");
        menu1.setCafeId(123L);
        menu1.setFiyat(1);
        menu1.setIcerik("Icerik");
        menu1.setId(123L);
        menu1.setUrunAdi("Urun Adi");
        when(iMenuRepository.save((Menu) any())).thenReturn(menu1);
        assertTrue(menuCommandServiceImp
                .addAndUpdateItemToMenu(new MenuDTO("Urun Adi",
                        "Icerik",
                        1,
                        "Ana Kategori",
                        "Alt Kategori",
                        "Baslik")));
        verify(modelMapper).map((Object) any(), (Class<Menu>) any());
        verify(iMenuRepository).save((Menu) any());
    }
}

