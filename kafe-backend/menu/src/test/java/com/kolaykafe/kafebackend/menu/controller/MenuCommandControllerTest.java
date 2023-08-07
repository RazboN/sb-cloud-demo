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
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

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
        menuDTO.setSubCategory("Alt Kategori");
        menuDTO.setMainCategory("Ana Kategori");
        menuDTO.setPrice(1);
        menuDTO.setItemName("Urun Adi");
    }

    /**
     * Method under test: {@link MenuCommandController#updateMenuItem(MenuDTO)}
     */
    @Test
    void testUpdateMenuItemShouldReturnTrue() throws Exception {
        when(menuCommandServiceImp.addAndUpdateItemToMenu(menuDTO)).thenReturn(true);

        HttpStatusCode result = undertTest.addMenuItem(menuDTO).getStatusCode();

        Assertions.assertEquals(HttpStatus.OK, result);
    }

    @Test
    void testUpdateMenuItemShouldReturnFalse() throws Exception {
        when(menuCommandServiceImp.addAndUpdateItemToMenu(menuDTO)).thenReturn(false);

        HttpStatusCode result = undertTest.addMenuItem(menuDTO).getStatusCode();;

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result);
    }
}

