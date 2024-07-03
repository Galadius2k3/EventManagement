package com.hutech.demo.service;

import com.hutech.demo.model.TicketItem;
import com.hutech.demo.model.Order;
import com.hutech.demo.model.OrderDetail;
import com.hutech.demo.repository.OrderDetailRepository;
import com.hutech.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final TicketService ticketService;

    public Order createOrder(String customerName, String phoneNumber, String email, String notes, String paymentMethod, List<TicketItem> ticketItems) {
        Order order = new Order();
        order.setCustomerName(customerName);
        order.setPhoneNumber(phoneNumber);
        order.setEmail(email);
        order.setNotes(notes);
        order.setPaymentMethod(paymentMethod);
        order = orderRepository.save(order);

        for (TicketItem item : ticketItems) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setEvent(item.getEvent());
            detail.setQuantity(item.getQuantity());
            orderDetailRepository.save(detail);
        }

        // Optionally clear the ticket after order placement
        ticketService.clearTicket();

        return order;
    }
}
