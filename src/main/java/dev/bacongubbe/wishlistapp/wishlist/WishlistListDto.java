package dev.bacongubbe.wishlistapp.wishlist;

import dev.bacongubbe.wishlistapp.wish.Wish;

import java.util.List;

public record WishlistListDto(
    List<Wish> wishes
) {
}
