package kgeu.slepchenko.bookstore.service;

import kgeu.slepchenko.bookstore.model.Book;
import kgeu.slepchenko.bookstore.model.CartItem;
import kgeu.slepchenko.bookstore.model.User;
import kgeu.slepchenko.bookstore.repository.CartItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SimpleCartItemService implements CartItemService {

    private final CartItemRepository cartItemRepository;

    private final BookService bookService;

    @Override
    public void addItemToCart(int bookId, User user) {
        CartItem cartItem = new CartItem();
        Book book = bookService.findById(bookId).get();
        cartItem.setBook(book);
        cartItem.setQuantity(1);
        cartItem.setPrice(book.getPrice());
        cartItem.setShoppingCart(user.getShoppingCart());
        System.err.println("000000000000000");
        System.err.println(cartItem.getShoppingCart().getId());
        System.err.println(cartItem.getId());
        System.err.println(cartItem.getBook().getId());
        System.err.println(cartItem.getShoppingCart().getUser().getShoppingCart().getId());
        cartItemRepository.addItemToCart(cartItem);
    }

    @Override
    public void removeItemFromCart(int itemId) {
        cartItemRepository.removeItemFromCart(itemId);
    }
}
