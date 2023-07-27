package com.kolaykafe.kafebackend.menu.service;

import com.kolaykafe.kafebackend.menu.repository.IMenuRepository;

import com.kolaykafe.kafebackend.menu.service.interfaces.MenuQueryService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
class MenuQueryServiceImpTest {
    @MockBean
    private IMenuRepository iMenuRepository;
    @MockBean
    private MenuQueryService menuQueryService;

    @MockBean
    private ModelMapper modelMapper;
//    @Test()
//    void testGetMenuByCafeId() {
//    }
}

