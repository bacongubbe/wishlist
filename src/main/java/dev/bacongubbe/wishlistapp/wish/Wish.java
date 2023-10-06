package dev.bacongubbe.wishlistapp.wish;

import dev.bacongubbe.wishlistapp.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Wish {
    @Id
    private String id;

    private String wish;

    private String link;

    @ManyToOne
    private User buyer;
}
