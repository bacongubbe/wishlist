package dev.bacongubbe.wishlistapp.wishlist;

import dev.bacongubbe.wishlistapp.wish.Wish;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @ManyToOne
    private User owner;

    @ManyToMany
    private List<User> subscribers;

    @OneToMany
    private List<Wish> wishes;

    public Wishlist(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }

    protected Wishlist() {
    }
}
