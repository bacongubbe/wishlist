package dev.bacongubbe.wishlistapp.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateUserDto(
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[\\p{IsAlphabetic}\\-'. ]+", message = "Invalid name")
    String name,

    @Email
    @NotNull
    String email
) {
}
