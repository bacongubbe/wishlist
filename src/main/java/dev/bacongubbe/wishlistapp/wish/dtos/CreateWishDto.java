package dev.bacongubbe.wishlistapp.wish.dtos;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CreateWishDto(
    @NotBlank(message = "Wish cannot be blank")
    @Pattern(regexp = "[^;{}\"^\\\\]*", message = "No hacking allowed.")
    String wish,
    @Nullable
    String link,

    String wishlistId
) {
}
