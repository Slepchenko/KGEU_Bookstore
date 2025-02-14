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
        cartItemRepository.addItemToCart(cartItem);
    }

    @Override
    public boolean removeAllItemsFromCart(int id) {
        cartItemRepository.removeAllItemsFromCart(id);
        return true;
    }
}
