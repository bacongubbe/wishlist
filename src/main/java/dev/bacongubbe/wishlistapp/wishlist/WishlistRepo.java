package dev.bacongubbe.wishlistapp.wishlist;

import dev.bacongubbe.wishlistapp.user.User;
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

    public WishlistListDto getWishlistsForUser(User owner) {
        return new WishlistListDto(repo.findByOwner(owner));
    }
}
