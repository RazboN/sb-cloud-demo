package com.kolaykafe.kafebackend.menu.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "menu")

public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="cafeId")
    private Long cafeId;

    @Column(name="urunAdi")
    private String urunAdi;

    @Column(name="icerik")
    private String icerik;

    @Column(name="fiyat")
    private int fiyat;

    @Column(name="anaKategori")
    private String anaKategori;

    @Column(name="altKategori")
    private String altKategori;

    @Column(name="baslik")
    private String baslik;
}