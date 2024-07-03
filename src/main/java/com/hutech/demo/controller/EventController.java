package com.hutech.demo.controller;

import com.hutech.demo.model.Event;
import com.hutech.demo.service.EventService;
import com.hutech.demo.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;
    @Autowired
    private TypeService typeService;  // Đảm bảo bạn đã inject TypeService

    // Hiển thị danh sách tất cả các sự kiện
    @GetMapping
    public String showEventList(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "/events/events-list";
    }

    // Hiển thị form để thêm mới sự kiện
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("event", new Event());
        model.addAttribute("types", typeService.getAllTypes());  // Load types
        return "/events/add-event";
    }

    // Xử lý form để thêm mới sự kiện
    @PostMapping("/add")
    public String addEvent(@Valid Event event, BindingResult result) {
        if (result.hasErrors()) {
            return "/events/add-event";
        }
        eventService.addEvent(event);
        return "redirect:/events";
    }

    // Hiển thị form để chỉnh sửa sự kiện
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Event event = eventService.getEventById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event Id:" + id));
        model.addAttribute("event", event);
        model.addAttribute("types", typeService.getAllTypes());  // Load types
        return "/events/update-event";
    }

    // Xử lý form để cập nhật sự kiện
    @PostMapping("/update/{id}")
    public String updateEvent(@PathVariable Long id, @Valid Event event, BindingResult result) {
        if (result.hasErrors()) {
            event.setId(id); // set id to keep it in the form in case of errors
            return "/events/update-event";
        }
        eventService.updateEvent(event);
        return "redirect:/events";
    }

    // Xử lý yêu cầu xóa sự kiện
    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventService.deleteEventById(id);
        return "redirect:/events";
    }
}
