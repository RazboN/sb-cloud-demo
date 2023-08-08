package com.kolaykafe.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String fullName;
    private String password;
    private String email;
    private String phoneNumber;
    private Boolean mailVerified = false;
}
