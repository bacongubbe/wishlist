package dev.bacongubbe.wishlistapp.wishlist.dtos;

import dev.bacongubbe.wishlistapp.user.User;
import dev.bacongubbe.wishlistapp.wish.dtos.OwnerWishDto;
import dev.bacongubbe.wishlistapp.wishlist.Wishlist;

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
