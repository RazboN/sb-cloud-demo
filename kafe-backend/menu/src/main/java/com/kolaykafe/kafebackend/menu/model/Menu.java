package com.kolaykafe.kafebackend.menu.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "MENU")

public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="CAFEID")
    private Long cafeId;

    @Column(name="ITEMNAME")
    private String itemName;

    @Column(name="PRICE")
    private int price;

    @Column(name="MAINCATEGORY")
    private String mainCategory;

    @Column(name="SUBCATEGORY")
    private String subCategory;
}