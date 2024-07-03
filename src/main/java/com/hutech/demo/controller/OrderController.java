package com.hutech.demo.controller;

import com.hutech.demo.model.TicketItem;
import com.hutech.demo.model.Order;
import com.hutech.demo.model.Event;
import com.hutech.demo.service.TicketService;
import com.hutech.demo.service.OrderService;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private TicketService ticketService;

    @GetMapping("/checkout")
    public String checkout() {
        return "/ticket/checkout";
    }

    @PostMapping("/submit")
    public String submitOrder(
            String customerName,
            String phoneNumber,
            String email,
            String notes,
            String paymentMethod) {
        List<TicketItem> ticketItems = ticketService.getTicketItems();
        if (ticketItems.isEmpty()) {
            return "redirect:/ticket"; // Redirect if ticket is empty
        }
        orderService.createOrder(customerName, phoneNumber, email, notes, paymentMethod, ticketItems);
        ticketService.clearTicket(); // Optionally clear the ticket after order placement
        return "redirect:/order/confirmation";
    }

    @GetMapping("/confirmation")
    public String orderConfirmation(Model model) {
        model.addAttribute("message", "Your order has been successfully placed.");
        return "ticket/order-confirmation";
    }
}

