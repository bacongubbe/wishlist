package dev.bacongubbe.wishlistapp.wishlist;

import java.util.List;

public record WishlistListDto(
    List<Wishlist> wishlists
) {
}
