package kgeu.slepchenko.bookstore.service;

import kgeu.slepchenko.bookstore.model.BookPurchase;

import java.util.Optional;

public interface BookPurchaseService {

    Optional<BookPurchase> create(BookPurchase bookPurchase);

    Optional<BookPurchase> findById(int id);

    Optional<BookPurchase> deleteById(int id);

}
