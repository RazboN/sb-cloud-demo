package com.kolaykafe.kafebackend.menu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuDTO {
    private Long cafeId;
    private String urunAdi;
    private String icerik;
    private int fiyat;
    private String anaKategori;
    private String altKategori;
    private String baslik;
}
