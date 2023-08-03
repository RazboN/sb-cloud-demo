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
    private String itemName;
    private int price;
    private String mainCategory;
    private String subCategory;
}
