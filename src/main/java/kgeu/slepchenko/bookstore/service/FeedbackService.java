package kgeu.slepchenko.bookstore.service;

import kgeu.slepchenko.bookstore.model.Feedback;

import java.util.Optional;

public interface FeedbackService {

    Optional<Feedback> save(Feedback feedback);

    Optional<Feedback> findById(int id);

    boolean delete(int id);
}
