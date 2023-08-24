package com.kolaycafe.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.util.UUID;

@Data
public class CafeDTO {
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
