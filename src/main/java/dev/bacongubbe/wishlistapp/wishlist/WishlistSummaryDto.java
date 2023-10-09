package dev.bacongubbe.wishlistapp.wishlist;

public record WishlistSummaryDto(
    String id,
    String name,
    int numberOfWishes,
    int numberOfSubscribers
) {
    public WishlistSummaryDto(Wishlist wishlist) {
        this(
            wishlist.getId(),
            wishlist.getName(),
            wishlist.getWishes().size(),
            wishlist.getSubscribers().size()
        );
    }
}
