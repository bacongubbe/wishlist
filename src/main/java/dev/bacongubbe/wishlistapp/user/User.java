package dev.bacongubbe.wishlistapp.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    private String id;
    private String name;
    private String email;

}
