package com.kolaykafe.kafebackend.menu.controller;

import com.kolaykafe.kafebackend.menu.dto.MenuDTO;
import com.kolaykafe.kafebackend.menu.service.MenuCommandServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;

//@ContextConfiguration(classes = {MenuCommandController.class})
@ExtendWith(MockitoExtension.class)
//@WebMvcTest(MenuCommandController.class)
class MenuCommandControllerTest {

    @Mock
    private MenuCommandServiceImp menuCommandServiceImp;
    @InjectMocks
    private MenuCommandController undertTest;
    private MenuDTO menuDTO;

    @BeforeEach
    void setup(){
        menuDTO = new MenuDTO();
        menuDTO.setAltKategori("Alt Kategori");
        menuDTO.setAnaKategori("Ana Kategori");
        menuDTO.setBaslik("Baslik");
        menuDTO.setFiyat(1);
        menuDTO.setIcerik("Icerik");
        menuDTO.setUrunAdi("Urun Adi");
    }

    /**
     * Method under test: {@link MenuCommandController#updateMenuItem(MenuDTO)}
     */
    @Test
    void testUpdateMenuItemShouldReturnTrue() throws Exception {
        when(menuCommandServiceImp.addAndUpdateItemToMenu(menuDTO)).thenReturn(true);

        boolean result = undertTest.updateMenuItem(menuDTO);

        Assertions.assertTrue(result);
    }

    @Test
    void testUpdateMenuItemShouldReturnFalse() throws Exception {
        when(menuCommandServiceImp.addAndUpdateItemToMenu(menuDTO)).thenReturn(false);

        boolean result = undertTest.updateMenuItem(menuDTO);

        Assertions.assertFalse(result);
    }
}
