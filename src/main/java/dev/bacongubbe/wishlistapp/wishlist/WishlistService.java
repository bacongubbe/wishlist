package dev.bacongubbe.wishlistapp.wishlist;

import org.springframework.stereotype.Service;

@Service
public class WishlistService {

    private final WishlistRepo repo;

    public WishlistService(WishlistRepo repo) {
        this.repo = repo;
    }

    public Wishlist createWishlist(Wishlist wishlist) {
        return repo.createWishlist(wishlist);
    }
}
