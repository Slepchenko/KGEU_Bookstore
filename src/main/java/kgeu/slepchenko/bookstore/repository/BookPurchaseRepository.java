package kgeu.slepchenko.bookstore.repository;

import kgeu.slepchenko.bookstore.model.BookPurchase;

import java.util.Optional;

public interface BookPurchaseRepository {

    Optional<BookPurchase> create(BookPurchase bookPurchase);

    Optional<BookPurchase> findById(int id);

    Optional<BookPurchase> deleteById(int id);

}
