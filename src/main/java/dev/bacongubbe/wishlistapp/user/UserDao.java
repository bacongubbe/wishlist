package dev.bacongubbe.wishlistapp.user;

import org.springframework.data.repository.ListCrudRepository;

public interface UserDao extends ListCrudRepository<User, String> {
}
