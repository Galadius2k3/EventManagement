package com.hutech.demo.service;

import com.hutech.demo.model.Feedback;
import com.hutech.demo.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class FeedbackService {

    private final FeedbackRepository FeedbackRepository;

    // Retrieve all Feedbacks from the database
    public List<Feedback> getAllFeedbacks() {
        return FeedbackRepository.findAll();
    }

    // Retrieve a Feedback by its id
    public Optional<Feedback> getFeedbackById(Long id) {
        return FeedbackRepository.findById(id);
    }

    // Add a new Feedback to the database
    public Feedback addFeedback(Feedback feedback) {
        return FeedbackRepository.save(feedback);
    }

    // Update an existing Feedback
    public Feedback updateFeedback(@NotNull Feedback feedback) {
        Feedback existingFeedback = FeedbackRepository.findById(feedback.getId())
                .orElseThrow(() -> new IllegalStateException("Feedback with ID " + feedback.getId() + " does not exist."));
        existingFeedback.setCustomerName(feedback.getCustomerName());
        existingFeedback.setEventName(feedback.getEventName());
        existingFeedback.setMess(feedback.getMess());
        existingFeedback.setCustomerEmail(feedback.getCustomerEmail());
        existingFeedback.setCustomerNumber(feedback.getCustomerNumber());
        return FeedbackRepository.save(existingFeedback);
    }

    // Delete a Feedback by its id
    public void deleteFeedbackById(Long id) {
        if (!FeedbackRepository.existsById(id)) {
            throw new IllegalStateException("Feedback with ID " + id + " does not exist.");
        }
        FeedbackRepository.deleteById(id);
    }
}

