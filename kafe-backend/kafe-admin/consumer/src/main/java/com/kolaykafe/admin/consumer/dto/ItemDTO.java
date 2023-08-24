package com.kolaykafe.admin.consumer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {
    private Long cafeId;
    private String itemName;
    private int price;
    private String mainCategory;
    private String subCategory;
}
