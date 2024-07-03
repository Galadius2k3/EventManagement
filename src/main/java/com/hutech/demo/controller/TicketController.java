package com.hutech.demo.controller;


import com.hutech.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public String showticket(Model model) {
        model.addAttribute("ticketItems", ticketService.getTicketItems());
        return "/ticket/ticket";
    }

    @PostMapping("/add")
    public String addToticket(@RequestParam Long eventId, @RequestParam int quantity) {
        ticketService.addToTicket(eventId, quantity);
        return "redirect:/ticket";
    }

    @GetMapping("/remove/{eventId}")
    public String removeFromticket(@PathVariable Long eventId) {
        ticketService.removeFromTicket(eventId);
        return "redirect:/ticket";
    }

    @GetMapping("/clear")
    public String clearticket() {
        ticketService.clearTicket();
        return "redirect:/ticket";
    }
}

