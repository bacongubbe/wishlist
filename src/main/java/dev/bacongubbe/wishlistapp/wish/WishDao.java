package dev.bacongubbe.wishlistapp.wish;

import org.springframework.data.repository.ListCrudRepository;

public interface WishDao extends ListCrudRepository<Wish, String> {
}
