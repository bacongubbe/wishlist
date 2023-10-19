package dev.bacongubbe.wishlistapp.wishlist.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateWishlistDto(
    @NotBlank
    @NotNull
    @Pattern(regexp = "[^;{}\"^\\\\]*")
    String name
) {
}
