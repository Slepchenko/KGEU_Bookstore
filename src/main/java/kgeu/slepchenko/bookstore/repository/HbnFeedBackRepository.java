package kgeu.slepchenko.bookstore.repository;

import kgeu.slepchenko.bookstore.model.Book;
import kgeu.slepchenko.bookstore.model.Feedback;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HbnFeedBackRepository implements FeedbackRepository {

    CrudRepository crudRepository;

    @Override
    public Optional<Feedback> save(Feedback feedback) {
        crudRepository.run(session -> session.merge(feedback));
        return Optional.of(feedback);
    }

    @Override
    public Optional<Feedback> findById(int id) {
        return crudRepository.optional(
                "from Feedback as f where f.id = :fId",
                Feedback.class,
                Map.of("fId", id));
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
