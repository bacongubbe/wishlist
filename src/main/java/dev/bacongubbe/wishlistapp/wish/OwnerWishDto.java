package dev.bacongubbe.wishlistapp.wish;

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
