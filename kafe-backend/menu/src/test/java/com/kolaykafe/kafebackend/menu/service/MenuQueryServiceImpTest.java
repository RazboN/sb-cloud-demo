package com.kolaykafe.kafebackend.menu.service;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.kolaykafe.kafebackend.menu.repository.IMenuRepository;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MenuQueryServiceImp.class})
@ExtendWith(SpringExtension.class)
class MenuQueryServiceImpTest {
    @MockBean
    private IMenuRepository iMenuRepository;

    @Autowired
    private MenuQueryServiceImp menuQueryServiceImp;

    @MockBean
    private ModelMapper modelMapper;

    /**
     * Method under test: {@link MenuQueryServiceImp#getMenuByCafeId(Long)}
     */
    @Test
    void testGetMenuByCafeId() {
        when(iMenuRepository.findByCafeId((Long) any())).thenReturn(new ArrayList<>());
        menuQueryServiceImp.getMenuByCafeId(123L);
        verify(iMenuRepository).findByCafeId((Long) any());
    }
}

