package com.hutech.demo.service;

import com.hutech.demo.model.TicketItem;
import com.hutech.demo.model.Event;
import com.hutech.demo.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
public class TicketService {
    private List<TicketItem> ticketItems = new ArrayList<>();

    @Autowired
    private EventRepository eventRepository;

    public void addToTicket(Long eventId, int quantity) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found: " + eventId));
        ticketItems.add(new TicketItem(event, quantity));
    }

    public List<TicketItem> getTicketItems() {
        return ticketItems;
    }

    public void removeFromTicket(Long eventId) {
        ticketItems.removeIf(item -> item.getEvent().getId().equals(eventId));
    }

    public void clearTicket() {
        ticketItems.clear();
    }
}

