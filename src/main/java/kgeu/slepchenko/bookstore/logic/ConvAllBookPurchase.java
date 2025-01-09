package kgeu.slepchenko.bookstore.logic;

import kgeu.slepchenko.bookstore.model.Book;
import kgeu.slepchenko.bookstore.model.CartItem;

import java.util.List;

public class ConvAllBookPurchase {

    public static String convert(List<CartItem> cartItems, int totalPrice) {
        StringBuilder sbResult = new StringBuilder();
        System.err.println("-----------------------" + cartItems.size());
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem ci = cartItems.get(i);
            Book b = ci.getBook();
            sbResult.append((i + 1) + ". Идентификационный номер книги: " + b.getId()).append(System.lineSeparator())
                    .append("Название книги: " + b.getName()).append(System.lineSeparator())
                    .append("Автор: " + b.getAuthor()).append(System.lineSeparator())
                    .append("Жанр: " + b.getCategory()).append(System.lineSeparator())
                    .append("Описание: " + b.getDescription()).append(System.lineSeparator())
                    .append("Количество: " + ci.getQuantity() + " штук")
                    .append("Цена книги: " + b.getPrice()).append(System.lineSeparator() + " рублей");
        }
        sbResult.append(System.lineSeparator())
                .append("Общая стоимость: " + totalPrice + " рублей")
                .append(System.lineSeparator());
        return sbResult.toString();
//        return "booki";
    }

}
