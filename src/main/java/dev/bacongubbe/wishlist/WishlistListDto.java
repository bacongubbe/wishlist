package dev.bacongubbe.wishlist;

import java.util.List;

public record WishlistListDto(
    List<Wish> wishes
) {
}
