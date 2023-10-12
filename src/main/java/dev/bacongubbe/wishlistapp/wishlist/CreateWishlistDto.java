package dev.bacongubbe.wishlistapp.wishlist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateWishlistDto(
    @NotBlank
    @NotNull
    String name
) {
}
