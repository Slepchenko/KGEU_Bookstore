package kgeu.slepchenko.bookstore.logic;

import kgeu.slepchenko.bookstore.model.Book;
import kgeu.slepchenko.bookstore.model.BookPurchase;
import kgeu.slepchenko.bookstore.model.CartItem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConvAllBookPurchase {

    public static String convertForSave(List<CartItem> cartItems, int totalPrice, LocalDateTime created) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String createdDate = created.format(formatter);
        StringBuilder sbResult = new StringBuilder();
        sbResult.append("Дата покупки: " + createdDate + "\n");
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem ci = cartItems.get(i);
            Book b = ci.getBook();
            sbResult.append((i + 1) + ". Идентификационный номер книги: " + b.getId()).append("\n")
                    .append("Название книги: " + b.getName()).append("\n")
                    .append("Автор: " + b.getAuthor()).append("\n")
                    .append("Жанр: " + b.getCategory().getName()).append("\n")
                    .append("Количество: " + ci.getQuantity()).append("\n")
                    .append("Цена книги: " + b.getPrice() + " рублей").append("\n");
        }
        sbResult.append("\n")
                .append("Общая стоимость: " + totalPrice + " рублей")
                .append("\n ------------------------\n");
        return sbResult.toString();
    }

    public static List<String> getListInfo(List<BookPurchase> bookPurchaseList) {
        return bookPurchaseList.stream()
                .map(BookPurchase::getAllBookPurchase)
                .flatMap(s -> Stream.concat(
                        Arrays.stream(s.split("\n")),
                        Stream.of("\n")
                )).collect(Collectors.toList());
    }

}
