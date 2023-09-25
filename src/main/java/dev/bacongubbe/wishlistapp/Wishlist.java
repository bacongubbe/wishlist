package dev.bacongubbe.wishlistapp;

import dev.bacongubbe.wishlistapp.user.User;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@EqualsAndHashCode
@Getter
@Setter
@Entity
public class Wishlist {

    @Id
    private String id;

    @ManyToOne
    private User owner;

    @ManyToMany
    private List<User> subscribers;

    @OneToMany
    private List<Wish> wishes;

}
