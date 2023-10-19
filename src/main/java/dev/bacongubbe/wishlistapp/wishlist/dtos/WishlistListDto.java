package dev.bacongubbe.wishlistapp.wishlist.dtos;

import java.util.List;

public record WishlistListDto(
    List<WishlistSummaryDto> wishlists
) {
}
