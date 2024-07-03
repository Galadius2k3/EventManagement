package com.hutech.demo.controller;

import com.hutech.demo.model.Feedback;
import com.hutech.demo.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;
    // Display a list of all Feedbacks
    @GetMapping
    public String showFeedbackList(Model model) {
        model.addAttribute("feedbacks", feedbackService.getAllFeedbacks());
        return "/feedbacks/feedbacks-list";
    }

    // For adding a new Feedback
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("feedback", new Feedback());
        return "/feedbacks/add-feedback";
    }

    // Process the form for adding a new Feedback
    @PostMapping("/add")
    public String addFeedback(@Valid Feedback feedback, BindingResult result) {
        if (result.hasErrors()) {
            return "/feedbacks/add-feedback";
        }
        feedbackService.addFeedback(feedback);
        return "redirect:/";
    }


    // Handle request to delete a Feedback
    @GetMapping("/delete/{id}")
    public String deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedbackById(id);
        return "redirect:/feedbacks";
    }
}
