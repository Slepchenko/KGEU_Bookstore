package kgeu.slepchenko.bookstore.service;

import kgeu.slepchenko.bookstore.logic.ConvAllBookPurchase;
import kgeu.slepchenko.bookstore.model.BookPurchase;
import kgeu.slepchenko.bookstore.model.CartItem;
import kgeu.slepchenko.bookstore.model.ShoppingCart;
import kgeu.slepchenko.bookstore.repository.BookPurchaseRepository;
import kgeu.slepchenko.bookstore.repository.CartItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleBookPurchaseService implements BookPurchaseService {

    private final BookPurchaseRepository bookPurchaseRepository;

    private final ShoppingCartService shoppingCartService;

    private final CartItemService cartItemService;

    @Override
    public Optional<BookPurchase> create(BookPurchase bookPurchase) {
        ShoppingCart shoppingCart = shoppingCartService.findCartById(bookPurchase.getUser().getShoppingCart().getId()).get();
        List<CartItem> cartItems = shoppingCart.getItems();
        bookPurchase.setPaid(shoppingCart.getTotalPrice());
        bookPurchase.setAllBookPurchase(ConvAllBookPurchase.convertForSave(cartItems, shoppingCart.getTotalPrice(), bookPurchase.getCreated()));
        Optional<BookPurchase> savedPurchase = bookPurchaseRepository.create(bookPurchase);
        cartItemService.removeAllItemsFromCart(shoppingCart.getId());
        shoppingCart.setTotalPrice(0);
        shoppingCartService.updateCart(shoppingCart);
        return savedPurchase;
    }

    @Override
    public Optional<BookPurchase> findById(int id) {
        Optional<BookPurchase> optionalBookPurchase = bookPurchaseRepository.findById(id);
        if (optionalBookPurchase.isEmpty()) {
            throw new RuntimeException("Покупка не найдена");
        }
        return optionalBookPurchase;
    }

    @Override
    public Optional<BookPurchase> deleteById(int id) {
        return bookPurchaseRepository.deleteById(id);
    }

    @Override
    public List<BookPurchase> findAllByUserId(int id) {
        List<BookPurchase> list = bookPurchaseRepository.findAllByUserId(id);
        if (list == null) {
            return List.of();
        }
        return list;
    }
}
