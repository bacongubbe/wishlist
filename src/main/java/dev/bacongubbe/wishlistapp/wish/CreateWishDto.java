package dev.bacongubbe.wishlistapp.wish;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public record CreateWishDto(
    @NotBlank(message = "Wish cannot be blank")
    String wish,
    @Nullable
    String link,

    String wishlistId
) {
}
