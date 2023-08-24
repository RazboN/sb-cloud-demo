package com.kolaykafe.admin.producer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {
    private Long cafeId;
    private String itemName;
    private double price;
    private String mainCategory;
    private String subCategory;
}
