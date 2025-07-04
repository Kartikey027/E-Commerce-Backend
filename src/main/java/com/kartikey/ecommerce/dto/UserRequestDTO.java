package com.kartikey.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {
    @NotBlank
    @Size(min=2,max = 50)
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min=6,max = 100)
    private String password;

    @NotBlank
    private String role;
}
