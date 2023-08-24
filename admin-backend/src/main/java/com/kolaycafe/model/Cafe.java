package com.kolaycafe.model;


import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import java.util.Date;
import java.util.UUID;


/**
 * adres eklenecek
 * lisans ouluşturma tarihi eklenecek
 *
 *
 * */

@Data
@AllArgsConstructor
@Document("Cafes")
public class Cafe {
    @Id
    private String id;
    private Date createDate = new Date();
    @NonNull
    @Size(min=1,max=50,message = "min=1 max=100")
    private String name;
    @NonNull
    @Size(min=1,max=50,message = "min=1 max=100")
    private String city;

    @NonNull
    @Size(min=1,max=50,message = "min=1 max=100")
    private String email;
    @NonNull
    private boolean licence;
    private UUID password = UUID.randomUUID();
}
