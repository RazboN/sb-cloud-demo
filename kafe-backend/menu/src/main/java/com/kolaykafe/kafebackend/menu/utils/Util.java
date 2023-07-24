package com.kolaykafe.kafebackend.menu.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Util {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
