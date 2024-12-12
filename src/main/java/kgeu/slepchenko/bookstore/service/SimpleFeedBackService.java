package kgeu.slepchenko.bookstore.service;

import kgeu.slepchenko.bookstore.model.Feedback;
import kgeu.slepchenko.bookstore.repository.FeedbackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleFeedBackService implements FeedbackService {

    FeedbackRepository feedbackRepository;

    @Override
    public Optional<Feedback> save(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public Optional<Feedback> findById(int id) {
        return feedbackRepository.findById(id);
    }

    @Override
    public boolean delete(int id) {
        return feedbackRepository.delete(id);
    }
}
