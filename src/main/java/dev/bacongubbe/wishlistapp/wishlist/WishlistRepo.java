package dev.bacongubbe.wishlistapp.wishlist;

import org.springframework.stereotype.Repository;

@Repository
public class WishlistRepo {

    private final WishlistDao repo;

    public WishlistRepo(WishlistDao repo) {
        this.repo = repo;
    }

    public Wishlist createWishlist(Wishlist wishlist) {
        return repo.save(wishlist);
    }
}
