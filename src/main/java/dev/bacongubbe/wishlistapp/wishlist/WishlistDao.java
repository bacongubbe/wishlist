package dev.bacongubbe.wishlistapp.wishlist;

import dev.bacongubbe.wishlistapp.user.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface WishlistDao extends ListCrudRepository<Wishlist, String> {
    List<Wishlist> findByOwner(User owner);
}
