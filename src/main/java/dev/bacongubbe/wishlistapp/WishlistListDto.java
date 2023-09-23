package dev.bacongubbe.wishlistapp;

import java.util.List;

public record WishlistListDto(
    List<Wish> wishes
) {
}
