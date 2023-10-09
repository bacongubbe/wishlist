package dev.bacongubbe.wishlistapp.wishlist;

import dev.bacongubbe.wishlistapp.user.User;
import dev.bacongubbe.wishlistapp.wish.OwnerWishDto;

import java.util.List;

public record OwnerWishlistDto(
    String id,
    String name,
    User owner,
    List<User> subscribers,
    List<OwnerWishDto> wishes
) {
    public OwnerWishlistDto(Wishlist wishlist) {
        this(
            wishlist.getId(),
            wishlist.getName(),
            wishlist.getOwner(),
            wishlist.getSubscribers(),
            wishlist.getWishes().stream().map(OwnerWishDto::new).toList()
        );
    }
}
