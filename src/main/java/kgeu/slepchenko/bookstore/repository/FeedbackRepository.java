package kgeu.slepchenko.bookstore.repository;

import kgeu.slepchenko.bookstore.model.Feedback;

import java.util.Optional;

public interface FeedbackRepository {

    Optional<Feedback> save(Feedback feedback);

    Optional<Feedback> findById(int id);

    boolean delete(int id);
}
