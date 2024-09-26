package kgeu.slepchenko.bookstore.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "book_purchase")
public class BookPurchase {

    private int id;

    private String name;

    private LocalDateTime created = LocalDateTime.now();

    @OneToMany
    @JoinColumn(name = "book_id")
    private List<Book> books = new ArrayList<>();

}
