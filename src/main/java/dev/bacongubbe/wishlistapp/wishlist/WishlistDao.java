package dev.bacongubbe.wishlistapp.wishlist;

import org.springframework.data.repository.ListCrudRepository;

public interface WishlistDao extends ListCrudRepository<Wishlist, String> {
}
