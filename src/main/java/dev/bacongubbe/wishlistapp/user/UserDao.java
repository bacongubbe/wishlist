package dev.bacongubbe.wishlistapp.user;

import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserDao extends ListCrudRepository<User, String> {

    public Optional<User> findUserByEmail(String email);
}
