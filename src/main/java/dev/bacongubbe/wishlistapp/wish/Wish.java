package dev.bacongubbe.wishlistapp.wish;

import dev.bacongubbe.wishlistapp.user.User;
import dev.bacongubbe.wishlistapp.wishlist.Wishlist;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Wish {
    @Id
    private String id;

    private String wish;

    private String link;

    @ManyToOne
    private User buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wishlist_id")
    private Wishlist wishlist;

    protected Wish() {
    }
}
