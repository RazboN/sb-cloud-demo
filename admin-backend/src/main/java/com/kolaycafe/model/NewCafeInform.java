package com.kolaycafe.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class NewCafeInform {

    @NotNull
    private String name;
    @NotNull
    private String city;
    @NotNull
    private boolean licence;
    @NotNull
    private String email;
    @NotNull
    private UUID password;
}