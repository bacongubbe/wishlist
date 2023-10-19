package dev.bacongubbe.wishlistapp.wish.dtos;

import dev.bacongubbe.wishlistapp.wish.Wish;

public record OwnerWishDto(
    String id,
    String wish,
    String link
) {
    public OwnerWishDto(Wish wish) {
        this(
            wish.getId(),
            wish.getWish(),
            wish.getLink()
        );
    }
}
