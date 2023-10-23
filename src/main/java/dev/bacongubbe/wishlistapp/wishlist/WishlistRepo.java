package dev.bacongubbe.wishlistapp.wishlist;

import dev.bacongubbe.wishlistapp.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class WishlistRepo {

    private final WishlistDao dao;

    public WishlistRepo(WishlistDao dao) {
        this.dao = dao;
    }

    public Wishlist saveWishlist(Wishlist wishlist) {
        return dao.save(wishlist);
    }

    public List<Wishlist> getWishlistsForUser(User owner) {
        return dao.findByOwner(owner);
    }

    public Wishlist getWishlistForOwner(User owner, String wishlistId) {
        return dao.findByOwnerAndId(owner, wishlistId)
            .orElseThrow(() -> new NoSuchElementException("Wishlist %s for user %s not found".formatted(wishlistId, owner.getName())));
    }

    public void delete(Wishlist wishList) {
        dao.delete(wishList);
    }
}
