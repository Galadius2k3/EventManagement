package com.hutech.demo.model;


public class TicketItem {
    private Event event;
    private int quantity;

    // Constructors
    public TicketItem(Event event, int quantity) {
        this.event = event;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
