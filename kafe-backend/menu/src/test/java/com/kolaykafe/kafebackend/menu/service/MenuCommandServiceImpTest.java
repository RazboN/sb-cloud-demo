package com.kolaykafe.kafebackend.menu.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.kolaykafe.kafebackend.menu.model.Menu;
import com.kolaykafe.kafebackend.menu.repository.IMenuRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class MenuCommandServiceImpTest {
    @MockBean
    private IMenuRepository iMenuRepository;

    @Test
    void testAddAndUpdateItemToMenuReturnsTrue() {
        Menu newItem = new Menu(4L,5L,
                "Test Ürün",
                100,
                "Ana Kategori",
                "Alt Kategori");


        when(iMenuRepository.save(newItem)).thenReturn(newItem);

        Menu testSaveResultObj = iMenuRepository.save(newItem);

        assertNotNull(testSaveResultObj);
    }

    @Test
    void testAddAndUpdateItemToMenuReturnsFalse() {
        Menu newItem = new Menu(4L,5L,
                "Test Ürün",
                100,
                "Ana Kategori",
                "Alt Kategori");


        when(iMenuRepository.save(newItem)).thenReturn(null);

        Menu testSaveResultObj = iMenuRepository.save(newItem);

        assertNull(testSaveResultObj);
    }
}

